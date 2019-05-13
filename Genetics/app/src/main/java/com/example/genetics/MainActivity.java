package com.example.genetics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.factories.StringFactory;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.StringCrossover;
import org.uncommons.watchmaker.framework.operators.StringMutation;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        // Create a factory to generate random 11-character Strings.
        char[] chars = new char[27];
        for (char c = 'A'; c <= 'Z'; c++)
        {
            chars[c - 'A'] = c;
        }
        chars[26] = ' ';
        CandidateFactory<String> factory = new StringFactory(chars, 11);

// Create a pipeline that applies cross-over then mutation.
        List<EvolutionaryOperator<String>> operators
                = new LinkedList<EvolutionaryOperator<String>>();
        operators.add(new StringMutation(chars, new Probability(0.02)));
        operators.add(new StringCrossover());
        EvolutionaryOperator<String> pipeline
                = new EvolutionPipeline<String>(operators);

        FitnessEvaluator<String> fitnessEvaluator = new StringEvaluator();
        SelectionStrategy<Object> selection = new RouletteWheelSelection();
        Random rng = new MersenneTwisterRNG();

        EvolutionEngine<String> engine
                = new GenerationalEvolutionEngine<String>(factory,
                pipeline,
                fitnessEvaluator,
                selection,
                rng);

        engine.addEvolutionObserver(new EvolutionObserver<String>()
        {
            public void populationUpdate(PopulationData<? extends String> data)
            {
                Log.v(TAG, "Generation " + data.getGenerationNumber() + ": " + data.getBestCandidate() + "\n");
            }
        });

        String result = engine.evolve(500, 100, new TargetFitness(1,true));

        textView.setText(result);
    }
}
