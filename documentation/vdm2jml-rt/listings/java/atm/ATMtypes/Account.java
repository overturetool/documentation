package atm.ATMtypes;

import java.util.*;
import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class Account implements Record, java.io.Serializable {
  public VDMSet cards;
  public Number balance;
  //@ public instance invariant atm.ATM.invChecksOn ==> inv_Account(cards,balance);

  public Account(final VDMSet _cards, final Number _balance) {

    //@ assert (V2J.isSet(_cards) && (\forall int i; 0 <= i && i < V2J.size(_cards); Utils.is_(V2J.get(_cards,i),atm.ATMtypes.Card.class)));

    //@ assert Utils.is_real(_balance);

    cards = _cards != null ? Utils.copy(_cards) : null;
    //@ assert (V2J.isSet(cards) && (\forall int i; 0 <= i && i < V2J.size(cards); Utils.is_(V2J.get(cards,i),atm.ATMtypes.Card.class)));

    balance = _balance;
    //@ assert Utils.is_real(balance);

  }
  /*@ pure @*/

  public boolean equals(final Object obj) {

    if (!(obj instanceof atm.ATMtypes.Account)) {
      return false;
    }

    atm.ATMtypes.Account other = ((atm.ATMtypes.Account) obj);

    return (Utils.equals(cards, other.cards)) && (Utils.equals(balance, other.balance));
  }
  /*@ pure @*/

  public int hashCode() {

    return Utils.hashCode(cards, balance);
  }
  /*@ pure @*/

  public atm.ATMtypes.Account copy() {

    return new atm.ATMtypes.Account(cards, balance);
  }
  /*@ pure @*/

  public String toString() {

    return "mk_ATM`Account" + Utils.formatFields(cards, balance);
  }
  /*@ pure @*/

  public VDMSet get_cards() {

    VDMSet ret_35 = cards;
    //@ assert atm.ATM.invChecksOn ==> ((V2J.isSet(ret_35) && (\forall int i; 0 <= i && i < V2J.size(ret_35); Utils.is_(V2J.get(ret_35,i),atm.ATMtypes.Card.class))));

    return ret_35;
  }

  public void set_cards(final VDMSet _cards) {

    //@ assert atm.ATM.invChecksOn ==> ((V2J.isSet(_cards) && (\forall int i; 0 <= i && i < V2J.size(_cards); Utils.is_(V2J.get(_cards,i),atm.ATMtypes.Card.class))));

    cards = _cards;
    //@ assert atm.ATM.invChecksOn ==> ((V2J.isSet(cards) && (\forall int i; 0 <= i && i < V2J.size(cards); Utils.is_(V2J.get(cards,i),atm.ATMtypes.Card.class))));

  }
  /*@ pure @*/

  public Number get_balance() {

    Number ret_36 = balance;
    //@ assert atm.ATM.invChecksOn ==> (Utils.is_real(ret_36));

    return ret_36;
  }

  public void set_balance(final Number _balance) {

    //@ assert atm.ATM.invChecksOn ==> (Utils.is_real(_balance));

    balance = _balance;
    //@ assert atm.ATM.invChecksOn ==> (Utils.is_real(balance));

  }
  /*@ pure @*/

  public Boolean valid() {

    return true;
  }
  /*@ pure @*/
  /*@ helper @*/

  public static Boolean inv_Account(final VDMSet _cards, final Number _balance) {

    return _balance.doubleValue() >= -1000L;
  }

  /*@ pure @*/
  /*@ helper @*/

  public static Boolean inv_ATM_Pin(final Object check_p) {

    Number p = ((Number) check_p);

    Boolean andResult_9 = false;

    if (0L <= p.longValue()) {
      if (p.longValue() <= 9999L) {
        andResult_9 = true;
      }
    }

    return andResult_9;
  }

  /*@ pure @*/
  /*@ helper @*/

  public static Boolean inv_ATM_AccountId(final Object check_id) {

    Number id = ((Number) check_id);

    return id.longValue() > 0L;
  }

  /*@ pure @*/
  /*@ helper @*/

  public static Boolean inv_ATM_Amount(final Object check_a) {

    Number a = ((Number) check_a);

    return a.longValue() < 2000L;
  }
}
