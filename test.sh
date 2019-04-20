if [[ $? -eq 0 ]]; then
  xdg-open $NAME.pdf
  NAME=`echo "$1" | cut -d'.' -f1`
  pdflatex -file-line-error -halt-on-error $NAME.tex && xdg-open $NAME.pdf
fi