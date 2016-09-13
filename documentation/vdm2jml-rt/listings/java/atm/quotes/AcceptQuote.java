package atm.quotes;

import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class AcceptQuote implements java.io.Serializable {
  private static int hc = 0;
  private static AcceptQuote instance = null;

  public AcceptQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AcceptQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AcceptQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AcceptQuote;
  }

  public String toString() {

    return "<Accept>";
  }
}
