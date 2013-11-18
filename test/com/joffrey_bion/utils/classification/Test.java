package com.jbion.utils.classification;

import java.util.ArrayList;

public class Test {

    private static final String DOG = "dog";
    private static final String CAT = "cat";

    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> classes = new ArrayList<>();
        classes.add(CAT);
        classes.add(DOG);
        ConfusionMatrix<String> cm = new ConfusionMatrix<>(classes);
        cm.add(DOG, DOG);
        cm.add(DOG, DOG);
        cm.add(DOG, DOG);
        cm.add(DOG, DOG);
        cm.add(DOG, DOG);
        cm.add(CAT, CAT);
        cm.add(DOG, DOG);
        cm.add(DOG, DOG);
        cm.add(CAT, CAT);
        cm.add(CAT, CAT);
        cm.add(DOG, DOG);
        cm.add(DOG, DOG);
        cm.add(CAT, DOG);
        cm.add(CAT, DOG);
        cm.add(CAT, DOG);
        cm.add(DOG, CAT);
        cm.add(DOG, CAT);
        cm.add(DOG, CAT);
        cm.add(DOG, CAT);
        cm.add(DOG, CAT);
        cm.add(CAT, CAT);
        cm.add(CAT, CAT);
        cm.add(CAT, CAT);
        cm.add(CAT, CAT);
        System.out.println("Recall dog: " + cm.getRecall(DOG));
        System.out.println("Precision dog: " + cm.getPrecision(DOG));
        System.out.println("F-Measure dog: " + cm.getFMeasure(DOG));
        System.out.println("Overall accuracy: " + cm.getCorrectRate());
        System.out.println("4/7 = " + 4.0/7.0);
        System.out.println("4/9 = " + 4.0/9.0);
        System.out.println(cm);
    }

}
