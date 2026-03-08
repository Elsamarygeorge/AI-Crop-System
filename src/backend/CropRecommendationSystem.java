package backend;

import java.io.*;
import java.util.*;

class CropModel {
    String crop;
    double prior;
    double[] mean = new double[7];
    double[] var = new double[7];

    CropModel(String crop,double prior,double[] mean,double[] var){
        this.crop = crop;
        this.prior = prior;
        this.mean = mean;
        this.var = var;
    }
}

class ClimateRange {
    double minTemp = 999, maxTemp = -999;
    double minHum = 999, maxHum = -999;
    double minRain = 999, maxRain = -999;
}

public class CropRecommendationSystem {

    private List<CropModel> models = new ArrayList<>();
    private Map<String,ClimateRange> climateMap = new HashMap<>();

    // Constructor loads model once
    public CropRecommendationSystem(){
        loadModel("data/trained_nb_model.csv");
        loadClimateRanges("data/crop_preprocessed.csv");
    }

    // Load trained model
    private void loadModel(String file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skip header
            String line;
            while((line = br.readLine()) != null){
                String[] v = line.split(",");
                String crop = v[0];
                double prior = Double.parseDouble(v[1]);
                double[] mean = new double[7];
                double[] var = new double[7];
                int idx = 2;
                for(int i=0;i<7;i++){
                    mean[i] = Double.parseDouble(v[idx++]);
                    var[i]  = Double.parseDouble(v[idx++]);
                }
                models.add(new CropModel(crop, prior, mean, var));
            }
        } catch(Exception e){ e.printStackTrace(); }
    }

    // Load climate ranges
    private void loadClimateRanges(String file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] v = line.split(",");
                double temp = Double.parseDouble(v[3]);
                double hum  = Double.parseDouble(v[4]);
                double rain = Double.parseDouble(v[6]);
                String crop = v[7].toLowerCase();
                climateMap.putIfAbsent(crop,new ClimateRange());
                ClimateRange r = climateMap.get(crop);
                r.minTemp = Math.min(r.minTemp,temp);
                r.maxTemp = Math.max(r.maxTemp,temp);
                r.minHum = Math.min(r.minHum,hum);
                r.maxHum = Math.max(r.maxHum,hum);
                r.minRain = Math.min(r.minRain,rain);
                r.maxRain = Math.max(r.maxRain,rain);
            }
        } catch(Exception e){ e.printStackTrace(); }
    }

    // Gaussian function
    private double gaussian(double x,double mean,double var){
        double exponent = Math.exp(-(Math.pow(x-mean,2))/(2*var));
        return (1/Math.sqrt(2*Math.PI*var))*exponent;
    }

    // Predict top 3 crops
    public List<String> getRecommendation(double n,double p,double k,
                                          double temp,double hum,
                                          double ph,double rain){

        double[] input = {n,p,k,temp,hum,ph,rain};
        Map<String,Double> probability = new HashMap<>();
        double total = 0;

        for(CropModel m : models){
            double prob = Math.log(m.prior);
            for(int i=0;i<7;i++){
                double g = gaussian(input[i], m.mean[i], m.var[i]);
                prob += Math.log(g + 1e-9);
            }
            prob = Math.exp(prob);
            probability.put(m.crop, prob);
            total += prob;
        }

        Map<String,Double> percent = new HashMap<>();
        for(String crop : probability.keySet()){
            percent.put(crop, (probability.get(crop)/total) * 100);
        }

        List<Map.Entry<String,Double>> list = new ArrayList<>(percent.entrySet());
        list.sort((a,b) -> Double.compare(b.getValue(), a.getValue()));

        List<String> result = new ArrayList<>();
        for(int i=0;i<3;i++){
            String crop = list.get(i).getKey();
            double score = list.get(i).getValue();
            String line = (i+1) + ". " +
                    crop.substring(0,1).toUpperCase() + crop.substring(1) +
                    " - " + String.format("%.2f", score) + "%";
            result.add(line);
        }
        return result;
    }

    // Return climate advisory for a crop
    public String getClimateAdvice(String crop,double temp,double hum,double rain){
        ClimateRange r = climateMap.get(crop.toLowerCase());
        if(r == null) return "No climate data available.";

        String advice = "";
        boolean issue = false;

        if(temp < r.minTemp){ advice += "Temperature lower than expected.\n"; issue = true;}
        else if(temp > r.maxTemp){ advice += "Temperature higher than expected.\n"; issue = true;}
        if(hum < r.minHum){ advice += "Humidity lower than expected.\n"; issue = true;}
        else if(hum > r.maxHum){ advice += "Humidity higher than expected.\n"; issue = true;}
        if(rain < r.minRain){ advice += "Rainfall lower than expected.\n"; issue = true;}
        else if(rain > r.maxRain){ advice += "Rainfall higher than expected.\n"; issue = true;}
        if(!issue) advice = "All climate parameters are suitable.";

        return advice;
    }
}
