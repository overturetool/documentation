# Much lazy

source=VDM10_lang_man

pdf:
#	latexmk -pdf $(source).tex  --synctex=1;
#	see $(source).pdf 2>/dev/null &
	platex $(source).tex
	dvipdfmx $(source) 2> ERR.out


clean:
	latexmk -c

clear:
	latexmk -C
