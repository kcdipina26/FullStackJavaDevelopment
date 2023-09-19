package com.techelevator;

public class Television {
    private boolean isOn;
    private int currentChannel;
    private int currentVolume;


    // Making a constructor
    public Television(){
        isOn = false;
        currentChannel = 3;
        currentVolume = 2;

    }
    // Method for TV control
      public void turnOn(){
        isOn = true;
        currentChannel = 3;
        currentVolume = 2;

      }
public void turnOff(){
        isOn = false;
}
 public boolean isOn() {
        return isOn;
 }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void changeChannel(int newChannel) {
      if (isOn && newChannel >= 3 && newChannel <= 18) {
          currentChannel = newChannel;
      }
  }

  public void channelUp(){
        if (isOn) {
            currentChannel = (currentChannel ==18) ? 3 : currentChannel +1;
        }
  }
   public void channelDown() {
        if(isOn) {
            currentChannel = (currentChannel ==3) ? 18 : currentChannel -1;

        }
}

  public void raiseVolume(){
        if(isOn && currentVolume < 10) {
            currentVolume++;
        }
}
 public void lowerVolume(){
        if(isOn && currentVolume > 0){
            currentVolume--;
        }
 }

}
