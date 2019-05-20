package GUI;

import javax.swing.*;

public class WatchIcon extends JLabel {
    private Boolean selected;


    public void setSelected(Boolean selected){
        this.selected = selected;
    }

    public Boolean getSelected() {
        return this.selected;
    }
}
