package atm.ATMtypes;

import java.util.*;
import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class Card implements Record, java.io.Serializable {
  public Number id;
  public Number pin;

  public Card(final Number _id, final Number _pin) {

    //@ assert Utils.is_nat(_id);

    //@ assert (Utils.is_nat(_pin) && inv_ATM_Pin(_pin));

    id = _id;
    //@ assert Utils.is_nat(id);

    pin = _pin;
    //@ assert (Utils.is_nat(pin) && inv_ATM_Pin(pin));

  }
  /*@ pure @*/

  public boolean equals(final Object obj) {

    if (!(obj instanceof atm.ATMtypes.Card)) {
      return false;
    }

    atm.ATMtypes.Card other = ((atm.ATMtypes.Card) obj);

    return (Utils.equals(id, other.id)) && (Utils.equals(pin, other.pin));
  }
  /*@ pure @*/

  public int hashCode() {

    return Utils.hashCode(id, pin);
  }
  /*@ pure @*/

  public atm.ATMtypes.Card copy() {

    return new atm.ATMtypes.Card(id, pin);
  }
  /*@ pure @*/

  public String toString() {

    return "mk_ATM`Card" + Utils.formatFields(id, pin);
  }
  /*@ pure @*/

  public Number get_id() {

    Number ret_33 = id;
    //@ assert atm.ATM.invChecksOn ==> (Utils.is_nat(ret_33));

    return ret_33;
  }

  public void set_id(final Number _id) {

    //@ assert atm.ATM.invChecksOn ==> (Utils.is_nat(_id));

    id = _id;
    //@ assert atm.ATM.invChecksOn ==> (Utils.is_nat(id));

  }
  /*@ pure @*/

  public Number get_pin() {

    Number ret_34 = pin;
    //@ assert atm.ATM.invChecksOn ==> ((Utils.is_nat(ret_34) && inv_ATM_Pin(ret_34)));

    return ret_34;
  }

  public void set_pin(final Number _pin) {

    //@ assert atm.ATM.invChecksOn ==> ((Utils.is_nat(_pin) && inv_ATM_Pin(_pin)));

    pin = _pin;
    //@ assert atm.ATM.invChecksOn ==> ((Utils.is_nat(pin) && inv_ATM_Pin(pin)));

  }
  /*@ pure @*/

  public Boolean valid() {

    return true;
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
