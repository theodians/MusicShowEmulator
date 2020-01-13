/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.judging;

import musicshow.contestants.Contestant;

/**
 *
 * @author nicolas
 */
public abstract class Judge {
    private String name;        

    public Judge(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public abstract double finalGrade (int grade, Contestant myContestant);
}
