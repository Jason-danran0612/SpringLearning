package com.threadEntity;

import com.entity.ThreadLocalSingleton;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @Classname ExecutorThread
 * @Description TODO
 * @Date 2020/4/4 17:14
 * @Created by ASUS
 */
public class ExecutorThread implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + ThreadLocalSingleton.getInstance());
    }
}
