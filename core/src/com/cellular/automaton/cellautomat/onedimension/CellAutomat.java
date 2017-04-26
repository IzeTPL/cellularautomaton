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
public class CellAutomat {
    
    public int[] rule;
    public int[][] states;
    public CellState cellState;
    public int steps;

    public CellAutomat() {

        rule = new int[8];
        cellState = new CellState();

    }

    public CellAutomat(int steps, int rule) {
    
        this.steps = steps;
        this.rule = new int[8];
        cellState = new CellState();
        init(steps, rule);
        
    }
    
    public void init(int steps, int rule) {

        for(int i = 0; i < 8; i++) {
            this.rule[i] = getBit(rule, i);
        }
        
    }
    
    public void startSimulation() {

        states = new int[steps][steps*2 - 1];
        states[0][steps - 1] = 1;

        for (int i = 0; i < steps-1; i++) {
    
            for(int j = 0; j < steps*2 - 1; j++) {
            
                for(int k = 0; k < 8; k++) {
                
                    if(j == 0) {
                    
                        if (CellState.triplets[k][0] == 0 && states[i][j] == CellState.triplets[k][1] && states[i][j+1] == CellState.triplets[k][2]) {
                    
                            states[i+1][j] = rule[k];
                    
                        }
                        
                    } else if(j == steps*2 - 2) {
                    
                        if (states[i][j-1] == CellState.triplets[k][0] && states[i][j] == CellState.triplets[k][1] && CellState.triplets[k][2] == 0) {
                    
                            states[i+1][j] = rule[k];
                    
                        }
                        
                    } else {                   
                        if (states[i][j-1] == CellState.triplets[k][0] && states[i][j] == CellState.triplets[k][1] && states[i][j+1] == CellState.triplets[k][2]) {
                    
                            states[i+1][j] = rule[k];
                    
                        }
                    }
                    
                }
                
            }              
            
        }
    
    }
    
    public void displayResults() {
    
        for (int i = 0; i < steps; i++) {
    
            for(int j = 0; j < steps*2 - 1; j++) {

                System.out.print(states[i][j]);
                
            }
                 
            System.out.println(" ");
            
        }
    }

    private int getBit(int n, int k) {
        return (n >> k) & 1;
    }

}
