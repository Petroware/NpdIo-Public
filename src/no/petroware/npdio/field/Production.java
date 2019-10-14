package no.petroware.npdio.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The monthly petroleum production for some entity.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class Production
{
  /** Production entries, one per month. Oldest first. */
  private final List<Entry> entries_ = new ArrayList<>();

  /**
   * Create a production instance.
   *
   * @param entries  The monthly production entries. Non-null.
   */
  Production(List<Entry> entries)
  {
    assert entries != null : "entries cannot be null";
    entries_.addAll(entries);
    Collections.sort(entries_, new EntryComparator());
  }

  /**
   * Return the production entries of this instance.
   * Oldest first.
   *
   * @return  The production entries. Never null.
   */
  public List<Entry> getEntries()
  {
    return Collections.unmodifiableList(entries_);
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuilder s = new StringBuilder();
    for (Entry entry : entries_)
      s.append(entry.toString() + '\n');
    return s.toString();
  }

  /**
   * One production entry, with total production for one month.
   * <p>
   * This class is thread-safe.
   *
   * @author <a href="mailto:info@petroware.no">Petroware AS</a>
   */
  public static final class Entry
  {
    /** YUear of production. */
    private final int year_;

    /** Month of production. [1,12]. */
    private final int month_;

    /** Net oil production. Million Sm\u00b3. */
    private final double oil_;

    /** Net gas production. Million Sm\u00b3. */
    private final double gas_;

    /** Net NGL production. Million Sm\u00b3. */
    private final double ngl_;

    /** Net condensate production. Million Sm\u00b3. */
    private final double condensate_;

    /** Net oil equivalent production. Million Sm\u00b3. */
    private final double oilEquivalents_;

    /** Water production. Million Sm\u00b3. */
    private final double water_;

    private final String npdidField_;

    /**
     * Create one production entry instance representing
     * the production of one month.
     *
     * @param year           Year of production.
     * @param month          Month of year. [1,12].
     * @param oil            Net oil production. Sm\u00b3.
     * @param gas            Net gas production. Sm\u00b3.
     * @param ngl            Net NGL production. Sm\u00b3.
     * @param condensate     Net condensate production. Sm\u00b3.
     * @param oilEquivalents Net oil equivalents production. Sm\u00b3.
     * @param water          Water production. Sm\u00b3.
     * @param npdidField     NPDID of the associated field.
     */
    Entry(int year,
          int month,
          double oil,
          double gas,
          double ngl,
          double condensate,
          double oilEquivalents,
          double water,
          String npdidField)
    {
      assert month >= 1 && month <= 12 : "Invalid month: " + month;

      year_ = year;
      month_ = month;
      oil_ = oil;
      gas_ = gas;
      ngl_ = ngl;
      condensate_ = condensate;
      oilEquivalents_ = oilEquivalents;
      water_ = water;
      npdidField_ = npdidField;
    }

    /**
     * Return year of this production entry.
     *
     * @return  Year of this production entry.
     */
    public int getYear()
    {
      return year_;
    }

    /**
     * Return month of this production entry.
     *
     * @return  Month of this production entry. [1,12].
     */
    public int getMonth()
    {
      return month_;
    }

    /**
     * Return net oil production in million Sm\u00b3.
     *
     * @return Net oil production.
     */
    public double getOil()
    {
      return oil_;
    }

    /**
     * Return net gas production in million Sm\u00b3.
     *
     * @return Net gas production.
     */
    public double getGas()
    {
      return gas_;
    }

    /**
     * Return net NGL production in million Sm\u00b3.
     *
     * @return Net NGL production.
     */
    public double getNgl()
    {
      return ngl_;
    }

    /**
     * Return net condesante production in million Sm\u00b3.
     *
     * @return Net condensate production.
     */
    public double getCondensate()
    {
      return condensate_;
    }

    /**
     * Return net oil equivalent production in million Sm\u00b3.
     *
     * @return Net oil equivalent production.
     */
    public double getOilEquivalents()
    {
      return oilEquivalents_;
    }

    /**
     * Return net water production in million Sm\u00b3.
     *
     * @return Net water production.
     */
    public double getWater()
    {
      return water_;
    }

    String getNpdidField()
    {
      return npdidField_;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
      return year_ + "." + month_ + " " + oilEquivalents_;
    }
  }

  /**
   * Comparator for production entry instances.
   *
   * @author <a href="mailto:info@petroware.no">Petroware AS</a>
   */
  private static final class EntryComparator
    implements Comparator<Entry>, Serializable
  {
    /** Version number for serialization. Not really used. */
    private static final long serialVersionUID = 1L;

    /** {@inheritDoc} */
    @Override
    public int compare(Entry entry1, Entry entry2)
    {
      int c = Integer.compare(entry1.year_, entry2.year_);
      return c == 0 ? Integer.compare(entry1.month_, entry2.month_) : c;
    }
  }
}
