package atm.quotes;

import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class startQuote implements java.io.Serializable {
  private static int hc = 0;
  private static startQuote instance = null;

  public startQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static startQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new startQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof startQuote;
  }

  public String toString() {

    return "<start>";
  }
}
