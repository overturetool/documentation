package atm.quotes;

import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class BusyQuote implements java.io.Serializable {
  private static int hc = 0;
  private static BusyQuote instance = null;

  public BusyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static BusyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new BusyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof BusyQuote;
  }

  public String toString() {

    return "<Busy>";
  }
}
