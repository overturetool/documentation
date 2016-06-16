# Much lazy

source=OvertureIDEUserGuide

pdf:
	latexmk -pdf $(source).tex --synctex=1;
	see $(source).pdf 2>/dev/null &

clean:
	latexmk -c

clear:
	latexmk -C
