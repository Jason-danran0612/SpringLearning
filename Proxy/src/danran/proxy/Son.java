package danran.proxy;

import javax.swing.plaf.synth.SynthSliderUI;

/**
 * @Classname Son
 * @Description TODO
 * @Date 2020/4/17 20:58
 * @Created by ASUS
 */
public class Son implements Person {
    @Override
    public void findLove() {
        System.out.println("Son finds the lover !!!");
    }

    @Override
    public void gte(String str) {
        System.out.println(str);
    }
}
