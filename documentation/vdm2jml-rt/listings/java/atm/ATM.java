package atm;

import java.util.*;
import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class ATM implements java.io.Serializable {
  /*@ spec_public @*/

  private static atm.ATMtypes.St St =
      new atm.ATMtypes.St(SetUtil.set(), null, false, MapUtil.map());
  /*@ public ghost static boolean invChecksOn = true; @*/

  private ATM() {}

  public static Tuple GetStatus() {

    if (!(Utils.equals(Utils.copy(St.get_currentCard()), null))) {
      if (St.get_pinOk()) {
        Tuple ret_1 = Tuple.mk_(false, "transaction in progress.");
        //@ assert (V2J.isTup(ret_1,2) && Utils.is_bool(V2J.field(ret_1,0)) && Utils.is_(V2J.field(ret_1,1),String.class));

        return Utils.copy(ret_1);

      } else {
        Tuple ret_2 = Tuple.mk_(false, "debit card inserted. Awaiting pin code.");
        //@ assert (V2J.isTup(ret_2,2) && Utils.is_bool(V2J.field(ret_2,0)) && Utils.is_(V2J.field(ret_2,1),String.class));

        return Utils.copy(ret_2);
      }

    } else {
      Tuple ret_3 = Tuple.mk_(true, "no debit card is currently inserted into the machine.");
      //@ assert (V2J.isTup(ret_3,2) && Utils.is_bool(V2J.field(ret_3,0)) && Utils.is_(V2J.field(ret_3,1),String.class));

      return Utils.copy(ret_3);
    }
  }
  //@ requires pre_OpenAccount(cards,id,St);
  //@ ensures post_OpenAccount(cards,id,\old(St.copy()),St);

  public static void OpenAccount(final VDMSet cards, final Number id) {

    //@ assert (V2J.isSet(cards) && (\forall int i; 0 <= i && i < V2J.size(cards); Utils.is_(V2J.get(cards,i),atm.ATMtypes.Card.class)));

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert St != null;

    St.set_accounts(
        MapUtil.munion(
            Utils.copy(St.get_accounts()),
            MapUtil.map(new Maplet(id, new atm.ATMtypes.Account(cards, 0.0)))));
  }
  //@ requires pre_AddCard(c,St);
  //@ ensures post_AddCard(c,\old(St.copy()),St);

  public static void AddCard(final atm.ATMtypes.Card c) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert St != null;

    St.set_validCards(SetUtil.union(Utils.copy(St.get_validCards()), SetUtil.set(Utils.copy(c))));
  }
  //@ requires pre_RemoveCard(c,St);
  //@ ensures post_RemoveCard(c,\old(St.copy()),St);

  public static void RemoveCard(final atm.ATMtypes.Card c) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert St != null;

    St.set_validCards(SetUtil.diff(Utils.copy(St.get_validCards()), SetUtil.set(Utils.copy(c))));
  }
  //@ requires pre_InsertCard(c,St);
  //@ ensures post_InsertCard(c,\result,\old(St.copy()),St);

  public static Object InsertCard(final atm.ATMtypes.Card c) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    if (SetUtil.inSet(c, Utils.copy(St.get_validCards()))) {
      //@ assert St != null;

      St.set_currentCard(Utils.copy(c));

      Object ret_4 = atm.quotes.AcceptQuote.getInstance();
      //@ assert (Utils.is_(ret_4,atm.quotes.AcceptQuote.class) || Utils.is_(ret_4,atm.quotes.BusyQuote.class) || Utils.is_(ret_4,atm.quotes.RejectQuote.class));

      return ret_4;

    } else if (!(Utils.equals(Utils.copy(St.get_currentCard()), null))) {
      Object ret_5 = atm.quotes.BusyQuote.getInstance();
      //@ assert (Utils.is_(ret_5,atm.quotes.AcceptQuote.class) || Utils.is_(ret_5,atm.quotes.BusyQuote.class) || Utils.is_(ret_5,atm.quotes.RejectQuote.class));

      return ret_5;

    } else {
      Object ret_6 = atm.quotes.RejectQuote.getInstance();
      //@ assert (Utils.is_(ret_6,atm.quotes.AcceptQuote.class) || Utils.is_(ret_6,atm.quotes.BusyQuote.class) || Utils.is_(ret_6,atm.quotes.RejectQuote.class));

      return ret_6;
    }
  }

  public static void Display(final String msg) {

    //@ assert Utils.is_(msg,String.class);

    IO.println(msg);
  }

  public static void NotifyUser(final Object outcome) {

    //@ assert (Utils.is_(outcome,atm.quotes.AcceptQuote.class) || Utils.is_(outcome,atm.quotes.BusyQuote.class) || Utils.is_(outcome,atm.quotes.RejectQuote.class));

    if (Utils.equals(outcome, atm.quotes.AcceptQuote.getInstance())) {
      Display("Card accepted");
    } else if (Utils.equals(outcome, atm.quotes.BusyQuote.getInstance())) {
      Display("Another card has already been inserted");
    } else {
      if (Utils.equals(outcome, atm.quotes.RejectQuote.getInstance())) {
        Display("Unknown card");
      } else {
        throw new RuntimeException("ERROR statement reached");
      }
    }
  }
  //@ requires pre_EnterPin(pin,St);

  public static void EnterPin(final Number pin) {

    //@ assert (Utils.is_nat(pin) && inv_ATM_Pin(pin));

    //@ assert St != null;

    St.set_pinOk(Utils.equals(St.get_currentCard().get_pin(), pin));
  }
  //@ requires pre_ReturnCard(St);
  //@ ensures post_ReturnCard(\old(St.copy()),St);

  public static void ReturnCard() {

    atm.ATMtypes.Card atomicTmp_1 = null;
    //@ assert ((atomicTmp_1 == null) || Utils.is_(atomicTmp_1,atm.ATMtypes.Card.class));

    Boolean atomicTmp_2 = false;
    //@ assert Utils.is_bool(atomicTmp_2);

    {
        /* Start of atomic statement */
      //@ set invChecksOn = false;

      //@ assert St != null;

      St.set_currentCard(Utils.copy(atomicTmp_1));

      //@ assert St != null;

      St.set_pinOk(atomicTmp_2);

      //@ set invChecksOn = true;

      //@ assert St.valid();

    } /* End of atomic statement */
  }
  //@ requires pre_Withdraw(id,amount,St);
  //@ ensures post_Withdraw(id,amount,\result,\old(St.copy()),St);

  public static Number Withdraw(final Number id, final Number amount) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert (Utils.is_nat1(amount) && inv_ATM_Amount(amount));

    final Number newBalance =
        ((atm.ATMtypes.Account) Utils.get(St.accounts, id)).get_balance().doubleValue()
            - amount.longValue();
    //@ assert Utils.is_real(newBalance);

    {
      VDMMap stateDes_1 = St.get_accounts();

      atm.ATMtypes.Account stateDes_2 = ((atm.ATMtypes.Account) Utils.get(stateDes_1, id));

      //@ assert stateDes_2 != null;

      stateDes_2.set_balance(newBalance);
      //@ assert (V2J.isMap(stateDes_1) && (\forall int i; 0 <= i && i < V2J.size(stateDes_1); (Utils.is_nat(V2J.getDom(stateDes_1,i)) && inv_ATM_AccountId(V2J.getDom(stateDes_1,i))) && Utils.is_(V2J.getRng(stateDes_1,i),atm.ATMtypes.Account.class)));

      //@ assert Utils.is_(St,atm.ATMtypes.St.class);

      //@ assert St.valid();

      Number ret_7 = newBalance;
      //@ assert Utils.is_real(ret_7);

      return ret_7;
    }
  }
  //@ requires pre_Deposit(id,amount,St);
  //@ ensures post_Deposit(id,amount,\result,\old(St.copy()),St);

  public static Number Deposit(final Number id, final Number amount) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert (Utils.is_nat1(amount) && inv_ATM_Amount(amount));

    final Number newBalance =
        ((atm.ATMtypes.Account) Utils.get(St.accounts, id)).get_balance().doubleValue()
            + amount.longValue();
    //@ assert Utils.is_real(newBalance);

    {
      VDMMap stateDes_3 = St.get_accounts();

      atm.ATMtypes.Account stateDes_4 = ((atm.ATMtypes.Account) Utils.get(stateDes_3, id));

      //@ assert stateDes_4 != null;

      stateDes_4.set_balance(newBalance);
      //@ assert (V2J.isMap(stateDes_3) && (\forall int i; 0 <= i && i < V2J.size(stateDes_3); (Utils.is_nat(V2J.getDom(stateDes_3,i)) && inv_ATM_AccountId(V2J.getDom(stateDes_3,i))) && Utils.is_(V2J.getRng(stateDes_3,i),atm.ATMtypes.Account.class)));

      //@ assert Utils.is_(St,atm.ATMtypes.St.class);

      //@ assert St.valid();

      Number ret_8 = newBalance;
      //@ assert Utils.is_real(ret_8);

      return ret_8;
    }
  }

  public static void PrintAccount(final Number id) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    final Number balance = ((atm.ATMtypes.Account) Utils.get(St.accounts, id)).get_balance();
    //@ assert Utils.is_real(balance);

    IO.printf("Balance is for account %s is %s\n", SeqUtil.seq(id, balance));
  }

  public static Number GetCurrentCardId() {

    if (!(Utils.equals(Utils.copy(St.get_currentCard()), null))) {
      Number ret_9 = St.get_currentCard().get_id();
      //@ assert ((ret_9 == null) || Utils.is_nat(ret_9));

      return ret_9;

    } else {
      Number ret_10 = null;
      //@ assert ((ret_10 == null) || Utils.is_nat(ret_10));

      return ret_10;
    }
  }

  public static Number TestCurrentCardId() {

    final Number id = GetCurrentCardId();
    //@ assert ((id == null) || Utils.is_nat(id));

    Number ret_11 = id;
    //@ assert ((ret_11 == null) || Utils.is_nat(ret_11));

    return ret_11;
  }

  public static Number TestStatus() {

    final Number accId = 1L;
    //@ assert Utils.is_nat1(accId);

    final atm.ATMtypes.Card c1 = new atm.ATMtypes.Card(1L, 1234L);
    //@ assert Utils.is_(c1,atm.ATMtypes.Card.class);

    {
      AddCard(Utils.copy(c1));
      OpenAccount(SetUtil.set(new atm.ATMtypes.Card(1L, 1234L)), accId);
      {
        final Tuple status = GetStatus();
        //@ assert (V2J.isTup(status,2) && Utils.is_bool(V2J.field(status,0)) && Utils.is_(V2J.field(status,1),String.class));

        final Boolean awaitingCard = ((Boolean) status.get(0));
        //@ assert Utils.is_bool(awaitingCard);

        final String msg = SeqUtil.toStr(status.get(1));
        //@ assert Utils.is_(msg,String.class);

        {
          IO.println("Message: " + msg);
          Boolean andResult_8 = false;
          //@ assert Utils.is_bool(andResult_8);

          if (awaitingCard) {
            if (Utils.equals(atm.quotes.AcceptQuote.getInstance(), InsertCard(Utils.copy(c1)))) {
              andResult_8 = true;
              //@ assert Utils.is_bool(andResult_8);

            }
          }

          if (andResult_8) {
            NotifyUser(atm.quotes.AcceptQuote.getInstance());
            EnterPin(1234L);
            return Deposit(accId, 100L);
          }
        }
      }

      Number ret_12 = 0L;
      //@ assert Utils.is_real(ret_12);

      return ret_12;
    }
  }

  public static Number TestWithdraw() {

    final Number accId = 1L;
    //@ assert Utils.is_nat1(accId);

    final Number cardId = 1L;
    //@ assert Utils.is_nat1(cardId);

    final Number pin = 1234L;
    //@ assert Utils.is_nat1(pin);

    final atm.ATMtypes.Card c1 = new atm.ATMtypes.Card(cardId, pin);
    //@ assert Utils.is_(c1,atm.ATMtypes.Card.class);

    {
      AddCard(Utils.copy(c1));
      OpenAccount(SetUtil.set(new atm.ATMtypes.Card(1L, 1234L)), accId);
      if (Utils.equals(InsertCard(Utils.copy(c1)), atm.quotes.AcceptQuote.getInstance())) {
        EnterPin(pin);
        {
          final Number expense = 600L;
          //@ assert Utils.is_nat1(expense);

          final Number profit = 100L;
          //@ assert Utils.is_nat1(profit);

          {
            final Number amount = expense.longValue() - profit.longValue();
            //@ assert Utils.is_nat1(amount);

            return Withdraw(accId, amount);
          }
        }
      }

      throw new RuntimeException("ERROR statement reached");
    }
  }

  public static Number TestTotalBalance() {

    final atm.ATMtypes.Card card1 = new atm.ATMtypes.Card(1L, 1234L);
    //@ assert Utils.is_(card1,atm.ATMtypes.Card.class);

    final atm.ATMtypes.Card card2 = new atm.ATMtypes.Card(2L, 5678L);
    //@ assert Utils.is_(card2,atm.ATMtypes.Card.class);

    final atm.ATMtypes.Account ac1 =
        new atm.ATMtypes.Account(SetUtil.set(Utils.copy(card1)), 1000L);
    //@ assert Utils.is_(ac1,atm.ATMtypes.Account.class);

    final atm.ATMtypes.Account ac2 = new atm.ATMtypes.Account(SetUtil.set(Utils.copy(card2)), 500L);
    //@ assert Utils.is_(ac2,atm.ATMtypes.Account.class);

    return TotalBalance(SetUtil.set(Utils.copy(ac1), Utils.copy(ac2)));
  }

  public static void TestScenario() {

    final Number accId1 = 1L;
    //@ assert (Utils.is_nat(accId1) && inv_ATM_AccountId(accId1));

    final Number pin1 = 1234L;
    //@ assert Utils.is_nat1(pin1);

    final atm.ATMtypes.Card card1 = new atm.ATMtypes.Card(1L, pin1);
    //@ assert Utils.is_(card1,atm.ATMtypes.Card.class);

    final Number pin2 = 2345L;
    //@ assert Utils.is_nat1(pin2);

    final atm.ATMtypes.Card card2 = new atm.ATMtypes.Card(2L, pin2);
    //@ assert Utils.is_(card2,atm.ATMtypes.Card.class);

    {
      AddCard(Utils.copy(card1));
      AddCard(Utils.copy(card2));
      OpenAccount(SetUtil.set(Utils.copy(card1), Utils.copy(card2)), accId1);
      {
        final Object ignorePattern_1 = InsertCard(Utils.copy(card2));
        //@ assert (Utils.is_(ignorePattern_1,atm.quotes.AcceptQuote.class) || Utils.is_(ignorePattern_1,atm.quotes.BusyQuote.class) || Utils.is_(ignorePattern_1,atm.quotes.RejectQuote.class));

        /* skip */
      }

      PrintAccount(accId1);
      EnterPin(2345L);
      {
        final Number ignorePattern_2 = Deposit(accId1, 200L);
        //@ assert Utils.is_real(ignorePattern_2);

        /* skip */
      }

      PrintAccount(accId1);
      ReturnCard();
      RemoveCard(Utils.copy(card1));
      RemoveCard(Utils.copy(card2));
    }
  }
  /*@ pure @*/

  public static Number TotalBalance(final VDMSet acs) {

    //@ assert (V2J.isSet(acs) && (\forall int i; 0 <= i && i < V2J.size(acs); Utils.is_(V2J.get(acs,i),atm.ATMtypes.Account.class)));

    if (Utils.empty(acs)) {
      Number ret_13 = 0L;
      //@ assert Utils.is_real(ret_13);

      return ret_13;

    } else {
      Number letBeStExp_1 = null;
      atm.ATMtypes.Account a = null;

      Boolean success_1 = false;
      //@ assert Utils.is_bool(success_1);

      VDMSet set_1 = Utils.copy(acs);
      //@ assert (V2J.isSet(set_1) && (\forall int i; 0 <= i && i < V2J.size(set_1); Utils.is_(V2J.get(set_1,i),atm.ATMtypes.Account.class)));

      for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext() && !(success_1); ) {
        a = ((atm.ATMtypes.Account) iterator_1.next());
        success_1 = true;
        //@ assert Utils.is_bool(success_1);

      }
      if (!(success_1)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      letBeStExp_1 =
          a.get_balance().doubleValue()
              + TotalBalance(SetUtil.diff(Utils.copy(acs), SetUtil.set(Utils.copy(a))))
                  .doubleValue();
      //@ assert Utils.is_real(letBeStExp_1);

      Number ret_14 = letBeStExp_1;
      //@ assert Utils.is_real(ret_14);

      return ret_14;
    }
  }
  /*@ pure @*/

  public static Number TotalBalanceMes(final VDMSet acs) {

    //@ assert (V2J.isSet(acs) && (\forall int i; 0 <= i && i < V2J.size(acs); Utils.is_(V2J.get(acs,i),atm.ATMtypes.Account.class)));

    Number ret_15 = acs.size();
    //@ assert Utils.is_nat(ret_15);

    return ret_15;
  }
  /*@ pure @*/

  public static Boolean pre_OpenAccount(
      final VDMSet cards, final Number id, final atm.ATMtypes.St St) {

    //@ assert (V2J.isSet(cards) && (\forall int i; 0 <= i && i < V2J.size(cards); Utils.is_(V2J.get(cards,i),atm.ATMtypes.Card.class)));

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_16 = !(SetUtil.inSet(id, MapUtil.dom(Utils.copy(St.get_accounts()))));
    //@ assert Utils.is_bool(ret_16);

    return ret_16;
  }
  /*@ pure @*/

  public static Boolean post_OpenAccount(
      final VDMSet cards, final Number id, final atm.ATMtypes.St _St, final atm.ATMtypes.St St) {

    //@ assert (V2J.isSet(cards) && (\forall int i; 0 <= i && i < V2J.size(cards); Utils.is_(V2J.get(cards,i),atm.ATMtypes.Card.class)));

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean andResult_1 = false;
    //@ assert Utils.is_bool(andResult_1);

    if (SetUtil.inSet(id, MapUtil.dom(Utils.copy(St.get_accounts())))) {
      if (Utils.equals(((atm.ATMtypes.Account) Utils.get(St.accounts, id)).get_balance(), 0L)) {
        andResult_1 = true;
        //@ assert Utils.is_bool(andResult_1);

      }
    }

    Boolean ret_17 = andResult_1;
    //@ assert Utils.is_bool(ret_17);

    return ret_17;
  }
  /*@ pure @*/

  public static Boolean pre_AddCard(final atm.ATMtypes.Card c, final atm.ATMtypes.St St) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_18 = !(SetUtil.inSet(c, Utils.copy(St.get_validCards())));
    //@ assert Utils.is_bool(ret_18);

    return ret_18;
  }
  /*@ pure @*/

  public static Boolean post_AddCard(
      final atm.ATMtypes.Card c, final atm.ATMtypes.St _St, final atm.ATMtypes.St St) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_19 = SetUtil.inSet(c, Utils.copy(St.get_validCards()));
    //@ assert Utils.is_bool(ret_19);

    return ret_19;
  }
  /*@ pure @*/

  public static Boolean pre_RemoveCard(final atm.ATMtypes.Card c, final atm.ATMtypes.St St) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_20 = SetUtil.inSet(c, Utils.copy(St.get_validCards()));
    //@ assert Utils.is_bool(ret_20);

    return ret_20;
  }
  /*@ pure @*/

  public static Boolean post_RemoveCard(
      final atm.ATMtypes.Card c, final atm.ATMtypes.St _St, final atm.ATMtypes.St St) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_21 = !(SetUtil.inSet(c, Utils.copy(St.get_validCards())));
    //@ assert Utils.is_bool(ret_21);

    return ret_21;
  }
  /*@ pure @*/

  public static Boolean pre_InsertCard(final atm.ATMtypes.Card c, final atm.ATMtypes.St St) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_22 = Utils.equals(Utils.copy(St.get_currentCard()), null);
    //@ assert Utils.is_bool(ret_22);

    return ret_22;
  }
  /*@ pure @*/

  public static Boolean post_InsertCard(
      final atm.ATMtypes.Card c,
      final Object RESULT,
      final atm.ATMtypes.St _St,
      final atm.ATMtypes.St St) {

    //@ assert Utils.is_(c,atm.ATMtypes.Card.class);

    //@ assert (Utils.is_(RESULT,atm.quotes.AcceptQuote.class) || Utils.is_(RESULT,atm.quotes.BusyQuote.class) || Utils.is_(RESULT,atm.quotes.RejectQuote.class));

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    if (Utils.equals(RESULT, atm.quotes.AcceptQuote.getInstance())) {
      Boolean ret_23 = Utils.equals(Utils.copy(St.get_currentCard()), c);
      //@ assert Utils.is_bool(ret_23);

      return ret_23;

    } else {
      if (Utils.equals(RESULT, atm.quotes.BusyQuote.getInstance())) {
        Boolean ret_24 =
            Utils.equals(Utils.copy(St.get_currentCard()), Utils.copy(_St.get_currentCard()));
        //@ assert Utils.is_bool(ret_24);

        return ret_24;

      } else {
        Boolean ret_25 = Utils.equals(Utils.copy(St.get_currentCard()), null);
        //@ assert Utils.is_bool(ret_25);

        return ret_25;
      }
    }
  }
  /*@ pure @*/

  public static Boolean pre_EnterPin(final Number pin, final atm.ATMtypes.St St) {

    //@ assert (Utils.is_nat(pin) && inv_ATM_Pin(pin));

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_26 = !(Utils.equals(Utils.copy(St.get_currentCard()), null));
    //@ assert Utils.is_bool(ret_26);

    return ret_26;
  }
  /*@ pure @*/

  public static Boolean pre_ReturnCard(final atm.ATMtypes.St St) {

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_27 = !(Utils.equals(Utils.copy(St.get_currentCard()), null));
    //@ assert Utils.is_bool(ret_27);

    return ret_27;
  }
  /*@ pure @*/

  public static Boolean post_ReturnCard(final atm.ATMtypes.St _St, final atm.ATMtypes.St St) {

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean andResult_2 = false;
    //@ assert Utils.is_bool(andResult_2);

    if (Utils.equals(Utils.copy(St.get_currentCard()), null)) {
      if (!(St.get_pinOk())) {
        andResult_2 = true;
        //@ assert Utils.is_bool(andResult_2);

      }
    }

    Boolean ret_28 = andResult_2;
    //@ assert Utils.is_bool(ret_28);

    return ret_28;
  }
  /*@ pure @*/

  public static Boolean pre_Withdraw(
      final Number id, final Number amount, final atm.ATMtypes.St St) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert (Utils.is_nat1(amount) && inv_ATM_Amount(amount));

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean andResult_3 = false;
    //@ assert Utils.is_bool(andResult_3);

    if (SetUtil.inSet(Utils.copy(St.get_currentCard()), Utils.copy(St.get_validCards()))) {
      Boolean andResult_4 = false;
      //@ assert Utils.is_bool(andResult_4);

      if (St.get_pinOk()) {
        Boolean andResult_5 = false;
        //@ assert Utils.is_bool(andResult_5);

        if (SetUtil.inSet(
            Utils.copy(St.get_currentCard()),
            Utils.copy(((atm.ATMtypes.Account) Utils.get(St.accounts, id)).get_cards()))) {
          if (SetUtil.inSet(id, MapUtil.dom(Utils.copy(St.get_accounts())))) {
            andResult_5 = true;
            //@ assert Utils.is_bool(andResult_5);

          }
        }

        if (andResult_5) {
          andResult_4 = true;
          //@ assert Utils.is_bool(andResult_4);

        }
      }

      if (andResult_4) {
        andResult_3 = true;
        //@ assert Utils.is_bool(andResult_3);

      }
    }

    Boolean ret_29 = andResult_3;
    //@ assert Utils.is_bool(ret_29);

    return ret_29;
  }
  /*@ pure @*/

  public static Boolean post_Withdraw(
      final Number id,
      final Number amount,
      final Number RESULT,
      final atm.ATMtypes.St _St,
      final atm.ATMtypes.St St) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert (Utils.is_nat1(amount) && inv_ATM_Amount(amount));

    //@ assert Utils.is_real(RESULT);

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    final atm.ATMtypes.Account accountPre =
        Utils.copy(((atm.ATMtypes.Account) Utils.get(_St.accounts, id)));
    //@ assert Utils.is_(accountPre,atm.ATMtypes.Account.class);

    final atm.ATMtypes.Account accountPost =
        Utils.copy(((atm.ATMtypes.Account) Utils.get(St.accounts, id)));
    //@ assert Utils.is_(accountPost,atm.ATMtypes.Account.class);

    Boolean andResult_6 = false;
    //@ assert Utils.is_bool(andResult_6);

    if (Utils.equals(
        accountPre.get_balance(), accountPost.get_balance().doubleValue() + amount.longValue())) {
      if (Utils.equals(accountPost.get_balance(), RESULT)) {
        andResult_6 = true;
        //@ assert Utils.is_bool(andResult_6);

      }
    }

    Boolean ret_30 = andResult_6;
    //@ assert Utils.is_bool(ret_30);

    return ret_30;
  }
  /*@ pure @*/

  public static Boolean pre_Deposit(
      final Number id, final Number amount, final atm.ATMtypes.St St) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert (Utils.is_nat1(amount) && inv_ATM_Amount(amount));

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    Boolean ret_31 = pre_Withdraw(id, amount, Utils.copy(St));
    //@ assert Utils.is_bool(ret_31);

    return ret_31;
  }
  /*@ pure @*/

  public static Boolean post_Deposit(
      final Number id,
      final Number amount,
      final Number RESULT,
      final atm.ATMtypes.St _St,
      final atm.ATMtypes.St St) {

    //@ assert (Utils.is_nat(id) && inv_ATM_AccountId(id));

    //@ assert (Utils.is_nat1(amount) && inv_ATM_Amount(amount));

    //@ assert Utils.is_real(RESULT);

    //@ assert Utils.is_(_St,atm.ATMtypes.St.class);

    //@ assert Utils.is_(St,atm.ATMtypes.St.class);

    final atm.ATMtypes.Account accountPre =
        Utils.copy(((atm.ATMtypes.Account) Utils.get(_St.accounts, id)));
    //@ assert Utils.is_(accountPre,atm.ATMtypes.Account.class);

    final atm.ATMtypes.Account accountPost =
        Utils.copy(((atm.ATMtypes.Account) Utils.get(St.accounts, id)));
    //@ assert Utils.is_(accountPost,atm.ATMtypes.Account.class);

    Boolean andResult_7 = false;
    //@ assert Utils.is_bool(andResult_7);

    if (Utils.equals(
        accountPre.get_balance().doubleValue() + amount.longValue(), accountPost.get_balance())) {
      if (Utils.equals(accountPost.get_balance(), RESULT)) {
        andResult_7 = true;
        //@ assert Utils.is_bool(andResult_7);

      }
    }

    Boolean ret_32 = andResult_7;
    //@ assert Utils.is_bool(ret_32);

    return ret_32;
  }

  public String toString() {

    return "ATM{" + "St := " + Utils.toString(St) + "}";
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
