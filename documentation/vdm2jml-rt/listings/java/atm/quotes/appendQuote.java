package atm.quotes;

import org.overture.codegen.runtime.*;
import org.overture.codegen.vdm2jml.runtime.*;

@SuppressWarnings("all")
//@ nullable_by_default

final public class appendQuote implements java.io.Serializable {
  private static int hc = 0;
  private static appendQuote instance = null;

  public appendQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static appendQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new appendQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof appendQuote;
  }

  public String toString() {

    return "<append>";
  }
}
