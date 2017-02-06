# Much lazy

source=VDM10_lang_man

pdf:
	latexmk -pdf $(source).tex  --synctex=1;
	see $(source).pdf 2>/dev/null &

clean:
	latexmk -c

clear:
	latexmk -C
