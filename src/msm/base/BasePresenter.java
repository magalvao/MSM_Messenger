/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msm.base;

/**
 *
 * @author mgalvao3
 */
public abstract class BasePresenter {
    
    protected BasePresenter() {
    }
    
    public static BasePresenter nullPresenter() {
        return new BasePresenter() {};
    }
    
    public void onCreate() {
        
    }
    
    public void onDestroy() {
    }
}
