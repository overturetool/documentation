package atm.quotes;

import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class RejectQuote implements java.io.Serializable {
  private static int hc = 0;
  private static RejectQuote instance = null;

  public RejectQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static RejectQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new RejectQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof RejectQuote;
  }

  public String toString() {

    return "<Reject>";
  }
}
