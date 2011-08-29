
import Common.Symbol;
import Fixtures.Symbols;
import Fixtures.waitForService;
import fit.Fixture;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author random
 */
public class TestRunner {
    public static void main(String args[]) throws MalformedURLException, IOException, InterruptedException, URISyntaxException {
        String url = "http://authorization-ci.dmz.arch.ecollege.com:3002/principal/[com:ecollege:whittaker:person]:4e332fa1e4b0beecb6bec929/resource/[com:ecollege:whittaker:coursesection]:4e56cd83e4b0cd012ea98a18/permission/delete?token=%superAdminToken1%";
        Fixture.setSymbol("superAdminToken1", "SomeValue");
        
        System.out.println(Symbol.parseSymbols("http://authorization-ci.dmz.arch.ecollege.com:3002/principal/[com:ecollege:whittaker:person]:4e332fa1e4b0beecb6bec929/resource/[com:ecollege:whittaker:coursesection]:4e56cd83e4b0cd012ea98a18/permission/delete?token=%superAdminToken1%"));
        
        waitForService fix = new waitForService();
        fix.serviceUrl = url;
        fix.tryUntil = "true";
        System.out.println(fix.foundMatch());
        System.out.println(fix.tryUntil);
    }
}
