package main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }
    public void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //full screen
            if(gp.fullScreenOn)
                bw.write("On");
            else
                bw.write("Off");
            bw.newLine();

            //music volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //se volume
            bw.write(String.valueOf(gp.soundEffect.volumeScale));
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            //full screen
            if(s.equals("On")){
                gp.fullScreenOn = true;
            }else  if(s.equals("Off")){
                gp.fullScreenOn = false;
            }

            //music volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //SE volume
            s = br.readLine();
            gp.soundEffect.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
