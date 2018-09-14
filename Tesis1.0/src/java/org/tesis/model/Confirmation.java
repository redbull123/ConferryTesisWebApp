package org.tesis.model;

/**
 *
 * @author rjsan
 */
public class Confirmation {
    
    boolean confirmation = false;

    public Confirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public Confirmation() {
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }
    
}
