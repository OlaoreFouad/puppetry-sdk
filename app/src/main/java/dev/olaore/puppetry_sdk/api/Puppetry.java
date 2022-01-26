package dev.olaore.puppetry_sdk.api;

import android.view.ViewGroup;

public class Puppetry {

    private static Puppetry puppetry;

    private Puppetry() {
    }

    /**
     * This method needs tobe called before using any other method. Use the returned object to make other calls.
     * Note that this can take long if the puppet is unused for more than a few  days. Call this in a
     * background thread to ensure there are no UI glitches.
     */
    public static Puppetry initailizeAndGet() {
        puppetry = new Puppetry();
        return puppetry;
    }

    /**
     * Diusplays the puppet in the specified view.
     */
    public void show(ViewGroup view) {
        // show puppetry in a view group
    }

    /**
     * Move the right arm to coordinates. Throws exception upon failure.
     */
    public void moveRightArm(float x, float y, float z) {
        // move right arm in direction of co-ordinates
    }

    /**
     * Move the left arm to coordinates. Throws exception upon failure.
     */
    public void moveLeftArm(float x, float y, float z) {
        // move left arm in direction of co-ordinates
    }

    /**
     * Asks the puppet to say the English phrase.
     */
    public void say(String phrase) {
        // say phrase.
    }

}
