\ProvidesClass{ase}

\LoadClass[a4paper,oneside,11pt]{memoir}


\usepackage[utf8]{inputenc} % Input encoding - Depending on editor
\usepackage{lmodern} % Modern LaTeX font
\usepackage[english]{babel} % Language package
\usepackage[T1]{fontenc} % Hyphenation
\usepackage{fix-cm} % Fix for cm
\usepackage{graphicx} % To handle pictures
\usepackage{xcolor} % To define colors
\usepackage{tikz} % Graphical tool
\usepackage{mathtools} % To use \eqref
\usepackage{url} % Use of urls in the text
\usepackage{varioref} % Smarter references
\usepackage{calc}% Auto calculate
\usepackage{lipsum} % Debugging text
\usepackage{sansmath,subfig} % Gives a warning because subfig loads caption



% *************** Include files ***************

\usepackage{listings}
\usepackage{times}
\usepackage{color}


% ************** Custom commands ******************


% ************************************************

\graphicspath{{figs/}} % Image path

\definecolor{ase_blue}{RGB}{10,55,136}


%-----------------------------------------------------------------------------%
% Global private variables and public setters
%-----------------------------------------------------------------------------%
\global\let\@subtitle\@empty
\global\let\@supervisor\@empty
\global\let\@cosupervisora\@empty
\global\let\@cosupervisorb\@empty
\global\let\@reportid\@empty
\global\let\@frontlogo\@empty

\newcommand\subtitle[1]{%
\def\@subtitle{#1}%
}%
\newcommand\supervisor[1]{%
\def\@supervisor{#1}%
}%
\newcommand\cosupervisora[1]{%
\def\@cosupervisora{#1}%
}%
\newcommand\cosupervisorb[1]{%
\def\@cosupervisorb{#1}%
}%
\newcommand\reportid[1]{%
\def\@reportid{#1}%
}%
\newcommand\frontlogo[1]{%
\def\@frontlogo{#1}%
}%

%---------------------------------------------------------------------------%
%-------------------------- PDF - PROPERTIES -------------------------------%
%---------------------------------------------------------------------------%
% Active links
\usepackage[plainpages=false,pdfpagelabels,	pageanchor=false]{hyperref}
\hypersetup{
    colorlinks,
    citecolor=black,
    filecolor=black,
    linkcolor=black,
    urlcolor=ase_blue
}

\AtBeginDocument{
%\ifx\subtitle\undefined
%\newcommand{\subtitle}{}
%\fi
%
%\ifx\reportid\undefined
%\newcommand{\reportid}{}
%\fi
%
%\ifx\supervisor\undefined
%\newcommand{\supervisor}{}
%\fi
%
%\ifx\cosupervisor\undefined
%\newcommand{\cosupervisor}{}
%\fi

\hypersetup{pdfauthor={\@author}, pdftitle={\@title},pdfsubject={\@subtitle}}

%define title page
\renewcommand{\@maketitle}{

\newpage
% Frontpage - Titlingpage from Memoir class
\begin{titlingpage}

  \thispagestyle{empty}

  % Temporary center margins
  \begin{adjustwidth}{-0.5cm}{0cm}
    \includegraphics[width=12cm]{st.png}%
    \vspace*{\stretch{0.5}}
    \centering
    { \setlength{\baselineskip}{32pt}
      {\Huge {\scshape\@title}
      }\par
      \stext{}
      \par\vspace*{2\onelineskip}
      \par
% \noindent\begin{minipage}{.48\textwidth}
% \vspace{0pt}
%     \centering
%     \includegraphics[height=4cm]{figs/fix_robo.jpg}
%   \tiny\textit{}
% \end{minipage}\hfill
% \begin{minipage}{.48\textwidth}
% \vspace{0pt}
%     \centering
%     \includegraphics[height=4cm]{figs/intelligentone.jpg}
%     \par
%   \tiny\textit{\copyright Intelligent Marking ApS}
% \end{minipage}
    \par\vspace{0.5cm}
	  \par	\logoHuge{\scshape\@subtitle} \par
      \par\vspace*{2\onelineskip}
      % Make a table to make the --- align
      \begin{center}
        \begin{tabular}{lcl}
		\logoHuge\scshape\@author\\[1ex]

          % To use danish letters with the UTF8 and soul package
          % we type \o = ø , \ae = æ, \aa = å, \AA = Å, etc.
          %\large\stext{S\o ren S\o rensen} & \large\stext{---} &
        \end{tabular}
        \par
        \small{Aarhus University - Department of Engineering}
      \end{center}
	    \par\vspace*{6\onelineskip}
	
    }
    \vfill
	%\ifthenelse{\equal{{\@frontlogo}}{}}{Empty.}{Noneempty}
	\ifx \@frontlogo \empty%
	%
	\else%
	\includegraphics[width=17cm]{\@frontlogo}%
	\fi%

%    \mbox{} %{\scshape\@date}

    \enlargethispage{3.5\onelineskip}
  \end{adjustwidth}
\end{titlingpage}




}

%--------------------------------------------------------------------------%
%------------------------- FRONT MATTER -----------------------------------%
%--------------------------------------------------------------------------%

%\frontmatter
%\include{frontpage} % Include the frontpage

% Page number in roman style
\pagenumbering{roman}

\newcommand\asetableofcontents{%
%\addcontentsline{toc}{chapter}{\abstractname}
%\include{abstract}
%\clearpage

\addcontentsline{toc}{chapter}{\contentsname}
\thispagestyle{empty}
\tableofcontents*

\clearpage

% \addcontentsline{toc}{chapter}{\listfigurename}
% \thispagestyle{empty}
% \listoffigures*
% \clearpage

%\addcontentsline{toc}{chapter}{\listtablename}
%\thispagestyle{empty}
%\listoftables*
}

}
%---------------------------------------------------------------------------%
%---------------------------- Caption Font ---------------------------------%
%---------------------------------------------------------------------------%
\DeclareCaptionFont{sansmath}{\sansmath}
\captionsetup{font={small,sf,sansmath}}
\captionsetup[subfloat]{font={small,sf,sansmath}}

%---------------------------------------------------------------------------%
%---------------------------- MARGIN CONTROL -------------------------------%
%---------------------------------------------------------------------------%
\setlrmarginsandblock{3.5cm}{2.5cm}{*}
\setulmarginsandblock{3cm}{*}{1.2}
\checkandfixthelayout[nearest]
\setlength{\evensidemargin}{\oddsidemargin}

%--------------------------------------------------------------------------%
%------------------------- FRONTPAGE - PROPERTIES -------------------------%
%--------------------------------------------------------------------------%
\usepackage{soul} % Letterspace package
\sodef\an{}{0.05em}{.5em plus.6em}{1em plus.1em minus.1em}
\newcommand\stext[1]{\an{\scshape#1}}
\newcommand{\logoHuge}{\fontsize{0.55cm}{0.8cm}\selectfont}
\newcommand{\SuperHuge}{\fontsize{1.2cm}{1.8cm}\selectfont}

%--------------------------------------------------------------------------%
%------------------------- PAGESTYLE - PROPERTIES -------------------------%
%--------------------------------------------------------------------------%
%\renewcommand{\chaptermark}[1]{\markboth{\MakeUppercase{#1}}{}}

\makepagestyle{ase_report}
\makeoddhead{ase_report}{}{\small\sffamily\leftmark}{}
\makeoddfoot{ase_report}{}{}{\small\sffamily\thepage}

\makeatletter
\makepsmarks{ase_report}{%
  \renewcommand\chaptermark[1]{%
    \markboth{%
      \ifnum \value{secnumdepth} > 1
      \if@mainmatter %
      \@chapapp\ \thechapter. \ %
      \fi
      \fi
      ##1}{}}%
  \renewcommand\tocmark{\markboth{\contentsname}{\contentsname}}%
  \renewcommand\lofmark{\markboth{\listfigurename}{\listfigurename}}%
  \renewcommand\lotmark{\markboth{\listtablename}{\listtablename}}%
  \renewcommand\bibmark{\markboth{\bibname}{\bibname}}%
  \renewcommand\indexmark{\markboth{\indexname}{\indexname}}%
  \renewcommand\sectionmark[1]{\markright{##1}}%
  \renewcommand\subsectionmark[1]{\markright{##1}}%
  \renewcommand\subsubsectionmark[1]{\markright{##1}}%
}

\copypagestyle{plain}{ase_report}
\makeoddhead{plain}{}{}{}
\makeoddfoot{plain}{}{}{\small\sffamily\thepage}

\pagestyle{ase_report}
\aliaspagestyle{chapter}{plain}

%--------------------------------------------------------------------------%
%--------------------- HEADING - SECTION ----------------------------------%
%--------------------------------------------------------------------------%
\newcommand{\ruledsec}[1]{%
  \Large\bfseries\sffamily\raggedright #1
  \color{ase_blue}\rule[15pt]{\textwidth}{1.0pt}} % Section with ruler
\setsecheadstyle{\ruledsec} % Define section head style

\setfloatlocations{figure}{htp}
\setfloatlocations{table}{htp}

%--------------------------------------------------------------------------%
%--------------------- HEADING - SUBs-SECTION -----------------------------%
%--------------------------------------------------------------------------%
\addtocounter{secnumdepth}{2} % Depth numbering

\setsubsecheadstyle{\large\bfseries\sffamily\raggedright}
\setsubsubsecheadstyle{\large\bfseries\sffamily\raggedright}

\setsechook{\hangsecnum} % Hang the section number in margin
\setsubsechook{\defaultsecnum} % Don't do this on the subsections
\setsubsubsechook{\defaultsecnum}
\setaftersecskip{0.2pt} % Default skip between the section and text



%--------------------------------------------------------------------------%
%------------------------- TOC - PROPERTIES -------------------------------%
%--------------------------------------------------------------------------%
\raggedbottomsectiontrue % The page may not be strected on page breaks
\setsecnumdepth{subsubsection} % Set section depth in the TOC
\maxsecnumdepth{subsubsection} % Max of section depth in the TOC
\settocdepth{section} % Up to and including subsection

\setlength{\cftbeforechapterskip}{1.0em plus 0.1em minus 0.1em} % Space from chapters
%\chapterprecistoc{Text in TOC}

\addto\captionsenglish{
  \renewcommand*{\cftchaptername}{Chapter{\space}}
  \renewcommand*{\cftfigurename}{Fig.{\space}}
  \renewcommand*{\contentsname}{Table of Contents}
  \renewcommand*{\abstractname}{Abstract}
  \renewcommand*{\listfigurename}{List{\space}of{\space}Figures}
  \renewcommand*{\listtablename}{List{\space}of{\space}Tables}
  \renewcommand*{\appendixtocname}{Appendices}
  \renewcommand*{\appendixpagename}{Appendices}
}

\addto\captionsdanish{
  \renewcommand*{\cftchaptername}{Kapitel\space}
  \renewcommand*{\cftfigurename}{Fig.\space}
  \renewcommand*{\abstractname}{Resum�}
  \renewcommand*{\contentsname}{Indholdsfortegnelse}
  \renewcommand*{\listfigurename}{Liste{\space}af{\space}Figurer}
  \renewcommand*{\listtablename}{Liste{\space}af{\space}Tabeller}
  \renewcommand*{\appendixtocname}{Appendikser}
  \renewcommand*{\appendixpagename}{Appendikser}
}
%--------------------------------------------------------------------------%
%------------------------- CHAPTER STYLE ----------------------------------%
%--------------------------------------------------------------------------%
\makechapterstyle{ase_chapterstyle}{
  \setlength{\beforechapskip}{30pt}
  \setlength{\afterchapskip}{1.5cm}
  \renewcommand*{\printchaptername}{}
  \renewcommand*{\chapnumfont}{\normalfont\sffamily\bfseries\fontsize{60}{0}\selectfont}
  \renewcommand*{\printchapternum}{
    \flushright
    \begin{tikzpicture}
      \draw[fill,color=ase_blue] (0,0) rectangle (2cm,2cm);
      \draw[color=white] (1cm,1cm) node { \chapnumfont\thechapter };
    \end{tikzpicture}
  }
  \renewcommand*{\chaptitlefont}{\normalfont\sffamily\Huge\bfseries\color{black}}
  \renewcommand*{\printchaptertitle}[1]{%
    \raggedright\chaptitlefont\parbox[t]{\textwidth}{\raggedright##1}}
}

\chapterstyle{ase_chapterstyle}

\makeatother
