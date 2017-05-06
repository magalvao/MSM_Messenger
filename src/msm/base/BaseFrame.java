/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.base;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author mgalvao3
 */
public abstract class BaseFrame<Presenter extends BasePresenter> extends JFrame {
    
    protected Presenter mPresenter;
    
    protected abstract Presenter createPresenter();

    public BaseFrame() {
        mPresenter = createPresenter();
        mPresenter.onCreate();
    }
    
    

    @Override
    public void dispose() {
        super.dispose();
        
    }
    
    
}
