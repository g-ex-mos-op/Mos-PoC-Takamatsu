package jp.co.isid.mos.bird.analysis.posdata.action;

import java.io.IOException;

public interface PosDataFormAction {
    
    public String initialize();
                   
    public String search();
    
    public String execute();
    
    //public void dataDownload(List outputList) throws IOException;
    
    public void cvsDownload() throws IOException;
    
    public void dataDownload() throws IOException;
}
