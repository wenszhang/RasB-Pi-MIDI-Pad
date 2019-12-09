package main;

import javax.sound.midi.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class PI_ano extends TimerTask {

    Synthesizer midiSynth;
    Instrument[] instr;
    MidiChannel[] mChannels;

    Map<Integer, Integer> noteMap;

    private int instrument = 79;

    public PI_ano() {
        try {
            midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            instr = midiSynth.getDefaultSoundbank().getInstruments();
            mChannels = midiSynth.getChannels();

            //instrument 80 is a synth thing
            midiSynth.loadInstrument(instr[instrument]);//load an instrument
            mChannels[0].programChange(instr[instrument].getPatch().getProgram()); //this

            noteMap = new HashMap<>();

        } catch (MidiUnavailableException e) {

        }
    }

    public void changeInstrument(int delta){
        instrument += delta;
        if(instrument > 127){
            instrument = 0;
        } else if (instrument < 0){
            instrument = 127;
        }
        midiSynth.loadInstrument(instr[instrument]);
        mChannels[0].programChange(instr[instrument].getPatch().getProgram());
    }
    public void run() {
        for (int freq : noteMap.keySet()) {
            System.out.println(noteMap.get(freq));
            if (noteMap.get(freq) > 0) {
                noteMap.put(freq, noteMap.get(freq)-1);
                mChannels[0].noteOn(freq, noteMap.get(freq));
            }

        }
    }

    public void playNote(int freq, int velocity) {
        noteMap.put(freq, velocity);
    }

//    public void playSound(int note, int velocity){
//        mChannels[0].noteOn(note, velocity);
//
//    }
//
//    public void stopSound(int note, int velocity){
//        mChannels[0].noteOff(note, velocity);
//    }
//
//    public void stopAllSounds(){
//        mChannels[0].allNotesOff();
//    }


//    public static void main(String[] args) {
//        main.PI_ano piano = new main.PI_ano();
//        piano.playSound(60, 50);
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}