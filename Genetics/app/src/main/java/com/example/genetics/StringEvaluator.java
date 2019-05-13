package com.example.genetics;

import android.util.Log;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

import info.debatty.java.stringsimilarity.CharacterSubstitutionInterface;
import info.debatty.java.stringsimilarity.WeightedLevenshtein;

public class StringEvaluator implements FitnessEvaluator<String>
{
    private final String targetString = "HELLO WORLD";

    /**
     * Assigns one "fitness point" for every character in the
     * candidate String that matches the corresponding position in
     * the target string.
     */
    public double getFitness(String candidate,
                             List<? extends String> population)
    {
        /*int matches = 0;
        for (int i = 0; i < candidate.length(); i++)
        {
            if (candidate.charAt(i) == targetString.charAt(i))
            {
                ++matches;
            }
        }*/

        WeightedLevenshtein wl = new WeightedLevenshtein(
                new CharacterSubstitutionInterface() {
                    public double cost(char c1, char c2) {

                        // The cost for substituting 't' and 'r' is considered
                        // smaller as these 2 are located next to each other
                        // on a keyboard
                        /*if (c1 == 't' && c2 == 'r') {
                            return 0.5;
                        }*/

                        // For most cases, the cost of substituting 2 characters
                        // is 1.0
                        return 1.0;
                    }
                });

        int maxLength = Math.max(candidate.length(), targetString.length());
        return (maxLength-wl.distance(candidate, targetString))/maxLength;
    }

    public boolean isNatural()
    {
        return true;
    }
}