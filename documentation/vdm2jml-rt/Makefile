
source=vdm2jml-tr

pdf:
	python gen-reg-tests.py
	latexmk -pdf $(source).tex --synctex=1
	see $(source).pdf 2>/dev/null &

clean:
	rm -rf test-copies
	latexmk -c

clear:
	rm -rf test-copies
	latexmk -C
