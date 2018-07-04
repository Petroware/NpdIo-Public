JavaPackages = \
        no/petroware/npdio \
        no/petroware/npdio/company \
        no/petroware/npdio/facility \
        no/petroware/npdio/field \
        no/petroware/npdio/license \
	no/petroware/npdio/survey \
        no/petroware/npdio/well \

JavadocPackages    = -subpackages no
JavadocWindowTitle = 'NPD I/O'
JavadocDocTitle    = 'NPD I/O - May 2018'
JavadocHeader      = 'NPD I/O'
JavadocFooter      = '<font size="-1">Copyright &copy; 2018 Petroware AS - <a href="https://petroware.no">https://petroware.no<a></font>'

include $(DEV_HOME)/tools/Make/Makefile
