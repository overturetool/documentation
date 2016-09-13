package atm.ATMtypes;

import java.util.*;
import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class St implements Record, java.io.Serializable {
  public VDMSet validCards;
  public atm.ATMtypes.Card currentCard;
  public Boolean pinOk;
  public VDMMap accounts;
  //@ public instance invariant atm.ATM.invChecksOn ==> inv_St(validCards,currentCard,pinOk,accounts);

  public St(
      final VDMSet _validCards,
      final atm.ATMtypes.Card _currentCard,
      final Boolean _pinOk,
      final VDMMap _accounts) {

    //@ assert (V2J.isSet(_validCards) && (\forall int i; 0 <= i && i < V2J.size(_validCards); Utils.is_(V2J.get(_validCards,i),atm.ATMtypes.Card.class)));

    //@ assert ((_currentCard == null) || Utils.is_(_currentCard,atm.ATMtypes.Card.class));

    //@ assert Utils.is_bool(_pinOk);

    //@ assert (V2J.isMap(_accounts) && (\forall int i; 0 <= i && i < V2J.size(_accounts); (Utils.is_nat(V2J.getDom(_accounts,i)) && inv_ATM_AccountId(V2J.getDom(_accounts,i))) && Utils.is_(V2J.getRng(_accounts,i),atm.ATMtypes.Account.class)));

    validCards = _validCards != null ? Utils.copy(_validCards) : null;
    //@ assert (V2J.isSet(validCards) && (\forall int i; 0 <= i && i < V2J.size(validCards); Utils.is_(V2J.get(validCards,i),atm.ATMtypes.Card.class)));

    currentCard = _currentCard != null ? Utils.copy(_currentCard) : null;
    //@ assert ((currentCard == null) || Utils.is_(currentCard,atm.ATMtypes.Card.class));

    pinOk = _pinOk;
    //@ assert Utils.is_bool(pinOk);

    accounts = _accounts != null ? Utils.copy(_accounts) : null;
    //@ assert (V2J.isMap(accounts) && (\forall int i; 0 <= i && i < V2J.size(accounts); (Utils.is_nat(V2J.getDom(accounts,i)) && inv_ATM_AccountId(V2J.getDom(accounts,i))) && Utils.is_(V2J.getRng(accounts,i),atm.ATMtypes.Account.class)));

  }
  /*@ pure @*/

  public boolean equals(final Object obj) {

    if (!(obj instanceof atm.ATMtypes.St)) {
      return false;
    }

    atm.ATMtypes.St other = ((atm.ATMtypes.St) obj);

    return (Utils.equals(validCards, other.validCards))
        && (Utils.equals(currentCard, other.currentCard))
        && (Utils.equals(pinOk, other.pinOk))
        && (Utils.equals(accounts, other.accounts));
  }
  /*@ pure @*/

  public int hashCode() {

    return Utils.hashCode(validCards, currentCard, pinOk, accounts);
  }
  /*@ pure @*/

  public atm.ATMtypes.St copy() {

    return new atm.ATMtypes.St(validCards, currentCard, pinOk, accounts);
  }
  /*@ pure @*/

  public String toString() {

    return "mk_ATM`St" + Utils.formatFields(validCards, currentCard, pinOk, accounts);
  }
  /*@ pure @*/

  public VDMSet get_validCards() {

    VDMSet ret_37 = validCards;
    //@ assert atm.ATM.invChecksOn ==> ((V2J.isSet(ret_37) && (\forall int i; 0 <= i && i < V2J.size(ret_37); Utils.is_(V2J.get(ret_37,i),atm.ATMtypes.Card.class))));

    return ret_37;
  }

  public void set_validCards(final VDMSet _validCards) {

    //@ assert atm.ATM.invChecksOn ==> ((V2J.isSet(_validCards) && (\forall int i; 0 <= i && i < V2J.size(_validCards); Utils.is_(V2J.get(_validCards,i),atm.ATMtypes.Card.class))));

    validCards = _validCards;
    //@ assert atm.ATM.invChecksOn ==> ((V2J.isSet(validCards) && (\forall int i; 0 <= i && i < V2J.size(validCards); Utils.is_(V2J.get(validCards,i),atm.ATMtypes.Card.class))));

  }
  /*@ pure @*/

  public atm.ATMtypes.Card get_currentCard() {

    atm.ATMtypes.Card ret_38 = currentCard;
    //@ assert atm.ATM.invChecksOn ==> (((ret_38 == null) || Utils.is_(ret_38,atm.ATMtypes.Card.class)));

    return ret_38;
  }

  public void set_currentCard(final atm.ATMtypes.Card _currentCard) {

    //@ assert atm.ATM.invChecksOn ==> (((_currentCard == null) || Utils.is_(_currentCard,atm.ATMtypes.Card.class)));

    currentCard = _currentCard;
    //@ assert atm.ATM.invChecksOn ==> (((currentCard == null) || Utils.is_(currentCard,atm.ATMtypes.Card.class)));

  }
  /*@ pure @*/

  public Boolean get_pinOk() {

    Boolean ret_39 = pinOk;
    //@ assert atm.ATM.invChecksOn ==> (Utils.is_bool(ret_39));

    return ret_39;
  }

  public void set_pinOk(final Boolean _pinOk) {

    //@ assert atm.ATM.invChecksOn ==> (Utils.is_bool(_pinOk));

    pinOk = _pinOk;
    //@ assert atm.ATM.invChecksOn ==> (Utils.is_bool(pinOk));

  }
  /*@ pure @*/

  public VDMMap get_accounts() {

    VDMMap ret_40 = accounts;
    //@ assert atm.ATM.invChecksOn ==> ((V2J.isMap(ret_40) && (\forall int i; 0 <= i && i < V2J.size(ret_40); (Utils.is_nat(V2J.getDom(ret_40,i)) && inv_ATM_AccountId(V2J.getDom(ret_40,i))) && Utils.is_(V2J.getRng(ret_40,i),atm.ATMtypes.Account.class))));

    return ret_40;
  }

  public void set_accounts(final VDMMap _accounts) {

    //@ assert atm.ATM.invChecksOn ==> ((V2J.isMap(_accounts) && (\forall int i; 0 <= i && i < V2J.size(_accounts); (Utils.is_nat(V2J.getDom(_accounts,i)) && inv_ATM_AccountId(V2J.getDom(_accounts,i))) && Utils.is_(V2J.getRng(_accounts,i),atm.ATMtypes.Account.class))));

    accounts = _accounts;
    //@ assert atm.ATM.invChecksOn ==> ((V2J.isMap(accounts) && (\forall int i; 0 <= i && i < V2J.size(accounts); (Utils.is_nat(V2J.getDom(accounts,i)) && inv_ATM_AccountId(V2J.getDom(accounts,i))) && Utils.is_(V2J.getRng(accounts,i),atm.ATMtypes.Account.class))));

  }
  /*@ pure @*/

  public Boolean valid() {

    return true;
  }
  /*@ pure @*/
  /*@ helper @*/

  public static Boolean inv_St(
      final VDMSet _validCards,
      final atm.ATMtypes.Card _currentCard,
      final Boolean _pinOk,
      final VDMMap _accounts) {

    Boolean success_2 = true;
    VDMSet v = null;
    atm.ATMtypes.Card c = null;
    Boolean p = null;
    VDMMap a = null;
    v = _validCards;
    c = _currentCard;
    p = _pinOk;
    a = _accounts;

    if (!(success_2)) {
      throw new RuntimeException("Record pattern match failed");
    }

    Boolean andResult_10 = false;

    Boolean orResult_1 = false;

    Boolean orResult_2 = false;

    if (p) {
      orResult_2 = true;
    } else {
      orResult_2 = !(Utils.equals(c, null));
    }

    if (!(orResult_2)) {
      orResult_1 = true;
    } else {
      orResult_1 = SetUtil.inSet(c, v);
    }

    if (orResult_1) {
      Boolean forAllExpResult_1 = true;
      VDMSet set_2 = MapUtil.dom(a);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext() && forAllExpResult_1; ) {
        Number id1 = ((Number) iterator_2.next());
        for (Iterator iterator_3 = set_2.iterator(); iterator_3.hasNext() && forAllExpResult_1; ) {
          Number id2 = ((Number) iterator_3.next());
          Boolean orResult_3 = false;

          if (!(!(Utils.equals(id1, id2)))) {
            orResult_3 = true;
          } else {
            orResult_3 =
                Utils.empty(
                    SetUtil.intersect(
                        Utils.copy(((atm.ATMtypes.Account) Utils.get(a, id1)).cards),
                        Utils.copy(((atm.ATMtypes.Account) Utils.get(a, id2)).cards)));
          }

          forAllExpResult_1 = orResult_3;
        }
      }
      if (forAllExpResult_1) {
        andResult_10 = true;
      }
    }

    return andResult_10;
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
