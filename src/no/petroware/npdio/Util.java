package no.petroware.npdio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A collection of utilities for the NpdIo library.
 * <p>
 * This class is public as a side-effect. It is used by the reader classes
 * and is not meant for client access.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class Util
{
  /** NPD date format description */
  private static final String DATE_FORMAT = "dd.MM.yyyy";

  /**
   * Private constructor to prevent instantiation.
   */
  private Util()
  {
    assert false : "This should never be called";
  }

  /**
   * Convert specified text to its corresponding NpdObject class.
   *
   * @param text  Text to convert. May be null.
   * @return      NpdObject class equivalent. Null if not found.
   */
  public static Class<? extends NpdObject> parseClass(String text)
  {
    if (text == null)
      return null;

    String kind = text.toLowerCase(Locale.US);
    if (kind.startsWith("field"))
      return no.petroware.npdio.field.NpdField.class;
    // TODO: More here if needed

    return null;
  }

  /**
   * Convert specified text to a boolean. If text is empty, return false.
   *
   * @param text  Text to convert. May be null.
   * @return      Boolean equivalent. True if text is "Y", "YES" or similar,
   *              false otherwise.
   */
  public static Boolean parseBoolean(String text)
  {
    if (text == null)
      return null;

    if (text.toLowerCase(Locale.US).startsWith("y"))
      return true;

    if (text.toLowerCase(Locale.US).startsWith("j"))
      return true;

    return false;
  }

  /**
   * Convert specified text to an integer.
   *
   * @param text  Text to convert. May be null.
   * @return      Integer equivalent. Null if text is null or empty.
   * @throws ParseException  If text cannot be converted to int.
   */
  public static Integer parseInt(String text)
    throws ParseException
  {
    if (text == null || text.isEmpty())
      return null;

    try {
      return Integer.parseInt(text);
    }
    catch (NumberFormatException exception) {
      throw new ParseException("Invalid integer: " + text, 0);
    }
  }

  /**
   * Convert specified text to a double.
   *
   * @param text  Text to convert. May be null.
   * @return      Double equivalent. Null if text is null or empty.
   * @throws ParseException  If text cannot be converted to double.
   */
  public static Double parseDouble(String text)
    throws ParseException
  {
    if (text == null || text.isEmpty())
      return null;

    try {
      return Double.parseDouble(text);
    }
    catch (NumberFormatException exception) {
      throw new ParseException("Invalid double: " + text, 0);
    }
  }

  /**
   * Convert specified text to a date.
   *
   * @param text  Text to convert.
   * @return      Date equivalent. null if text is empty.
   * @throws ParseException  If text cannot be converted to a date.
   */
  public static Date parseDate(String text)
    throws ParseException
  {
    if (text == null || text.isEmpty())
      return null;

    // SimpleDateFromat is not thread-safe so we create it locally
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    return dateFormat.parse(text);
  }

  /**
   * Split the specified line on commas. Tokens may optionally be enclosed
   * in qoutes if for instance they contain commas themselves.
   *
   * <pre>
   *   This, is,,a,"silly, but",actual,"example"
   * </pre>
   *
   * <p>
   * Splits into:
   *
   * <pre>
   *   "This"
   *   " is"
   *   ""
   *   "a"
   *   "silly, but"
   *   "actual"
   *   "example"
   * </pre>
   *
   * @param line  Line to CSV split. Non-null.
   * @return  Individual tokens. Never null.
   * @throws IllegalArgumentException  If line is null.
   */
  public static String[] csvSplit(String line)
  {
    if (line == null)
      throw new IllegalArgumentException("line cannot be null");

    boolean inQuote = false;

    // Locate the positions of the separating commas
    // include positions just before and after the string too.
    List<Integer> splitPos = new ArrayList<>();
    splitPos.add(-1);
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (c == '\"')  /*"*/
        inQuote = !inQuote;
      else if (c == ',' && !inQuote)
        splitPos.add(i);
    }
    splitPos.add(line.length());

    // Find number of tokens and prepare return structure
    int nTokens = splitPos.size() - 1;
    String[] tokens = new String[nTokens];

    // Split string on split positions
    // Trim and un-quote if necessary
    for (int i = 0; i < splitPos.size() - 1; i++) {
      int from = splitPos.get(i) + 1;
      int to = splitPos.get(i + 1);
      String token = line.substring(from, to).trim();
      if (token.startsWith("\"") && token.endsWith("\""))
        token = token.substring(1, token.length() - 1);

      tokens[i] = token;
    }

    return tokens;
  }
}

