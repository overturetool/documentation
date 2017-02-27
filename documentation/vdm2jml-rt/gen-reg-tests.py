
import os, glob, distutils.dir_util, shutil

def realPath(root, f):
    return os.path.realpath(os.path.join(dirname,f)).replace("%s/" % os.getcwd(), "")

testCopies = "test-copies"
if os.path.isdir(testCopies):
    shutil.rmtree(testCopies)

testFolder = "/home/peter/git-repos/ovt/core/codegen/vdm2jml/src/test/resources/dynamic_analysis"
distutils.dir_util.copy_tree(testFolder, testCopies)

outputFile = "chapters/generated.tex"

out = open(outputFile, "w")

testNo = 0

for dirname, dirnames, filenames in os.walk(testCopies):
    if not dirnames:
        testNo = testNo + 1;
        for f in filenames:
            if f.endswith(".vdmsl"):
                out.write("\\section{%s}\n" % f)
                out.write("%% Test number %s\n\n" % testNo)
                out.write("\\subsection{The VDM-SL model}")
                out.write("\\lstinputlisting[language=VDM_SL,style=appendixVdm,breaklines=true]{%s}\n\n" % realPath(dirname, f))

        for f in filenames:
            if f.endswith(".java"):
                out.write("\\subsection{The generated Java/JML}")
                out.write("\\lstinputlisting[style=customJml]{%s}\n\n" % realPath(dirname, f))

        for f in filenames:
            if f.endswith(".result"):
                out.write("\\subsection{The OpenJML runtime assertion checker output}")
                out.write("\\lstinputlisting[style=racOutput]{%s}\n\n" % realPath(dirname, f))

                
out.write("\n\n")
out.write("%%% Local Variables:\n")
out.write("%%% mode: latex\n")
out.write("%%% TeX-master: \"../vdm2jml-tr\"\n")
out.write("%%% End:\n")
out.write("\n\n")
        
out.write
        
out.close()

print 'Finished generating %s.' % outputFile
