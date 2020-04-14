package org.mal_lang.scllang.test;

import core.Asset;
import core.AttackStep;
import core.Attacker;
import core.Defense;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TestSCLLang {
  @Test
  public void testSubnetToAccessPoint() {

    //Assets
    var subNet = new SubNetwork("subNet");
    var accessPoint = new AccessPoint("accessPoint");

    //Asset connections and attacker creation
    subNet.addAccessPoint(accessPoint);

    var attacker = new Attacker();
    attacker.addAttackPoint(subNet.access);
    attacker.attack();

    //Assertions
    accessPoint.communicate.assertCompromisedInstantaneously();
  }
  @Test
  public void accessPointToServer() {

    //Assets
    var accessPoint = new AccessPoint("accessPoint");
    var server = new Server("server");

    //Asset connections and attacker creation
    accessPoint.addServer(server);

    var attacker = new Attacker();
    attacker.addAttackPoint(accessPoint.communicate);
    attacker.attack();

    //Assertions
    server.communicate.assertCompromisedInstantaneously();
  }
 @Test
  public void usecase4noRouter() {

    //Assets
    var subNet = new SubNetwork("subNet");
    var accessPoint1 = new AccessPoint("accessPoint");
    var accessPoint2 = new AccessPoint("accessPoint");

    var ied = new IED("ied");

    //Asset connections and attacker creation
    accessPoint2.addSubNet(subNet);
    accessPoint1.addIed(ied);
    accessPoint2.addIed(ied);

    var attacker = new Attacker();
    attacker.addAttackPoint(subNet.access);
    attacker.attack();

    //Assertions
    accessPoint1.communicate.assertUncompromised();

 }

 @Test
  public void usecase4Router() {

  //Assets
  var subNet = new SubNetwork("subNet");
  var accessPoint1 = new AccessPoint("accessPoint");
  var accessPoint2 = new AccessPoint("accessPoint");
  var ied = new IED("ied");
  var router = new Router("router");

  //Asset connections and attacker creation
  accessPoint2.addSubNet(subNet);
  accessPoint2.addRouter(router);
  accessPoint1.addIed(ied);
  accessPoint2.addIed(ied);

  var attacker = new Attacker();
  attacker.addAttackPoint(subNet.access);
  attacker.attack();

  //Assertions
  accessPoint1.communicate.assertCompromisedInstantaneously();

 }

  @AfterEach
  public void deleteModel() {
    Asset.allAssets.clear();
    AttackStep.allAttackSteps.clear();
    Defense.allDefenses.clear();
   }
}
