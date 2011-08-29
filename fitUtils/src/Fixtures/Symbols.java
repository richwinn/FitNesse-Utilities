/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fixtures;

import fit.ColumnFixture;
import fit.Fixture;

/**
 *
 * @author Richard Winn
 */
public class Symbols extends ColumnFixture {
    public String key;
    public String value;
    
    public String set() {
        Fixture.setSymbol(key, value);
        return Fixture.getSymbol(key).toString();
    }
    public String get() {
        value = Fixture.getSymbol(key).toString();
        return value;
    }
}
