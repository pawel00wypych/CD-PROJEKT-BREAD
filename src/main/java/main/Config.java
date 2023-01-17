package main;

import java.io.*;

public class Config {

    ConfigMemento memento;

    public Config(ConfigMemento memento) {
        this.memento = memento;
    }


    public void saveConfig(ConfigMemento memento){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/config/config.txt", true));

            //full screen
            if(memento.fullScreenOn)
                bw.write("On");
            else
                bw.write("Off");
            bw.newLine();

            //music volume
            bw.write(String.valueOf(memento.musicVolume));
            bw.newLine();

            //se volume
            bw.write(String.valueOf(memento.effectVolume));
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ConfigMemento loadConfig(){
        try {
            boolean fullScreenOn;
            int musicVolume;
            int effectVolume;

            InputStream resourceStream =  this.getClass().getResourceAsStream("/config/config.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(resourceStream));
            String s = br.readLine();

            //full screen
            if(s.equals("On")){
                fullScreenOn = true;
            }else{
                fullScreenOn = false;
            }

            //music volume
            s = br.readLine();
            musicVolume = Integer.parseInt(s);

            //SE volume
            s = br.readLine();
            effectVolume = Integer.parseInt(s);
            resourceStream.close();
            br.close();
            return new ConfigMemento(fullScreenOn,musicVolume,effectVolume);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
