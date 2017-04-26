/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellautomat.onedimension;

/**
 *
 * @author mslupek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CellAutomat cellAutomat = new CellAutomat(8, 90);
        cellAutomat.startSimulation();
        cellAutomat.displayResults();
        
    }
    
}
