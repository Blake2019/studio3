package com.aptana.editor.css.parsing;

import java.util.ArrayList;
import java.util.List;

import beaver.Parser;
import beaver.ParsingTables;
import beaver.Scanner;
import beaver.Symbol;

import com.aptana.editor.css.parsing.ast.CSSAttributeSelectorNode;
import com.aptana.editor.css.parsing.ast.CSSCharSetNode;
import com.aptana.editor.css.parsing.ast.CSSCommentNode;
import com.aptana.editor.css.parsing.ast.CSSDeclarationNode;
import com.aptana.editor.css.parsing.ast.CSSErrorDeclarationNode;
import com.aptana.editor.css.parsing.ast.CSSErrorExpressionNode;
import com.aptana.editor.css.parsing.ast.CSSExpressionNode;
import com.aptana.editor.css.parsing.ast.CSSFunctionNode;
import com.aptana.editor.css.parsing.ast.CSSImportNode;
import com.aptana.editor.css.parsing.ast.CSSMediaNode;
import com.aptana.editor.css.parsing.ast.CSSPageNode;
import com.aptana.editor.css.parsing.ast.CSSRuleNode;
import com.aptana.editor.css.parsing.ast.CSSSimpleSelectorNode;
import com.aptana.editor.css.parsing.ast.CSSTermListNode;
import com.aptana.editor.css.parsing.ast.CSSTermNode;
import com.aptana.parsing.IParseState;
import com.aptana.parsing.IParser;
import com.aptana.parsing.ast.IParseRootNode;
import com.aptana.parsing.ast.ParseRootNode;
import com.aptana.parsing.lexer.IRange;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "CSS.grammar".
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CSSParser extends Parser implements IParser {

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9obLkTt55KK$ytE1Xo1H0n8mBX0B6H9Deb9I8naav2sAXRiKXGL2oAeO1SvyWTuZfpZ9p$" +
		"b4oZOKEnTx0BsGYnOi5TYhrn$xxwtkyEoIwBEdD$SE$VTzkwxyzu0tlLeHWkwC1hZiIyIw4" +
		"GJgZ42X#CmlhUZ1hMemw6eHmCQCGsL6AiMIx4Vpa07sZ235d6CL8lYF1Rdu2piWrASXpDnh" +
		"hDefVqrM8OhSBfinq5OZbwSZ6in4JqOWoYAC0p3iHS2PfJ4KSme4h9OZJ9QnDHYCbAO6hQG" +
		"dRXKO#vFJpPc2Ix3vI9EFaKsMhayQUGu0BEmDyRH$oZh7wVWIipDo6TIdetziMPx5MFxE0v" +
		"7u#fTihKH9k1i5$aaZM2bvQoGbNQhzFXCX3cCS20EnY6OZVaPwO9mNDQXYd6hDUxvkWBXk4" +
		"su08jq1IwK9tPR0QEzc1MUZnFa4rQuIHx74jK6JiohtGjp7KDTq7UAJwkDtRJjtCnbvvOAp" +
		"JWhHKZQdP7QEfZh21p9sMUvRan21Unrh79TRiHqw4BMmNWwZIFMqqMu69UucDETp7HEBpM1" +
		"KpcFfQJBC8#Lp79WvnIwKl3OSt7Mk9Zza#2y0yuxO9#Fudi9EteqluzIzjXOVWdZsDM1MbH" +
		"oV2hxU18xC#2g5gb5LnwB25To1cti6bgB2jh7QL$5lCtwrdBSrDhm7bUYcQlGmhMOhXdvpA" +
		"WZPD5BVxNCrrh4#2s6BQ3v#RH8nonYdut8oCrjvRxAoxWZJE1NLCcEjFvD9MepSe$ot6mdk" +
		"jZ5$4h2iMs4Q2PoL2EafIETDAxI81EXan5ibqKPAIrDx18t4Uf8Jps6umRif1vTWNBUkIjW" +
		"j8rD9NCt#GVKqHNcNb6SNrju6Lq#0yJuL6tIVDg#ZjYvnR8ppaf9itM8PtIr3ggbiyzo76b" +
		"5KlIo72LsnDIXd7KmdZZYF9azPOgDoGwrCgFfeSxE0vMajf8PZhHT2boS1x1YfVr65fhlsl" +
		"25eBq5n1nVGYmsF1tjTBBwF3OfZlz2kjgzhr#PtWc0JiVFTVH7eZLaQ$QJVfEfUz#nf$m60" +
		"zCVpaVPqFMvLuDxDd2ZUx#1M4K$WNk$oT4Dn83Zrpcwd7fB7B$HnMUzlRYJrP2V7OgrDa$y" +
		"hTH9Elu7OfFZDnFRyinlmH1gq1ZoNqei6imc3Ix3Tc9Wy1WOxsYresj2yKjoT5VbYTDFKf5" +
		"h#r$0U3mLlFR$uyFv6JE4ULz7lSavidcKpNJpCltT5nhhA#2dRq$ZERfRXf3xBAUxmXBvM$" +
		"wKF#Ht#Kr#rMyJyhC#VoAOetmcdyhJyfJJPkV9W78xYE#L#q0#Klg#lCVdE$AMl2dtotroh" +
		"zmZ6#HkkKlMovroXzmkjwdUvz8lZycZyeWyB5lbRNbNjZdl7phw7V6jSjyGNolt5V4byOQy" +
		"9Y$BAx95Dl7jHNb1dfVdLEDPVJwZprlaPbaZc#KbkLNMoLfvLHwI1#K1#K9Ubx#eC8TdggT" +
		"$GntSHSt9MLy0JTntcnLHdZu6HJo3VFrFQr5#64yaduZoNFA9g9wB2VqBi7y2hOg8qYHfah" +
		"IHj96qWJeDj6aWtqQ#ZV8svJtTpslqJy4dF9w74Vrpg2AjKkfnPuyGvgmpY3Yattqz0xl86" +
		"qnpw75epq5d27N$4jKXT8SG$mS4WHW4");

		// suppress the error printouts
		private static class CSSEvents extends Events
		{
	
			public void scannerError(Scanner.Exception e)
			{
			}
	
			public void syntaxError(Symbol token)
			{
			}
	
			public void unexpectedTokenRemoved(Symbol token)
			{
			}
	
			public void missingTokenInserted(Symbol token)
			{
			}
	
			public void misspelledTokenReplaced(Symbol token)
			{
			}
	
			public void errorPhraseRemoved(Symbol error)
			{
			}
		}
		
	public synchronized IParseRootNode parse(IParseState parseState) throws java.lang.Exception
	{
		CSSScanner scanner = new CSSScanner();
		scanner.setSource(new String(parseState.getSource()));
		ParseRootNode result = (ParseRootNode) parse(scanner);
		int start = parseState.getStartingOffset();
		int end = start + parseState.getSource().length;
		result.setLocation(start, end);
		List<CSSCommentNode> commentNodes = new ArrayList<CSSCommentNode>();
		IRange[] comments = scanner.getComments();
		for (IRange comment : comments)
		{
			commentNodes.add(new CSSCommentNode(comment.getStartingOffset(), comment.getEndingOffset()));
		}
		result.setCommentNodes(commentNodes.toArray(new CSSCommentNode[commentNodes.size()]));
		parseState.setParseResult(result);
		return result;
	}

	public CSSParser() {
		super(PARSING_TABLES);


		report = new CSSEvents();
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		switch(rule_num) {
			case 0: // Program = Statements.p
			{
					final Symbol _symbol_p = _symbols[offset + 1];
					final ArrayList _list_p = (ArrayList) _symbol_p.value;
					final beaver.Symbol[] p = _list_p == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_p.toArray(new beaver.Symbol[_list_p.size()]);
					
			return new ParseRootNode(ICSSParserConstants.LANGUAGE, p, _symbol_p.getStart(), _symbol_p.getEnd());
			}
			case 1: // Program = 
			{
					
			return new ParseRootNode(ICSSParserConstants.LANGUAGE, new Symbol[0], 0, 0);
			}
			case 2: // Statements = Statements Statement
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2]); return _symbols[offset + 1];
			}
			case 3: // Statements = Statement
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
			}
			case 10: // CharSet = CHARSET.c STRING.s SEMICOLON.e
			{
					final Symbol c = _symbols[offset + 1];
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					final Symbol e = _symbols[offset + 3];
					
			return new CSSCharSetNode(s, c.getStart(), e.getEnd());
			}
			case 11: // Import = IMPORT.i ImportWord.s SEMICOLON.e
			{
					final Symbol i = _symbols[offset + 1];
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					final Symbol e = _symbols[offset + 3];
					
			return new CSSImportNode(s, i.getStart(), e.getEnd());
			}
			case 12: // Import = IMPORT.i ImportWord.s List.m SEMICOLON.e
			{
					final Symbol i = _symbols[offset + 1];
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					final Symbol _symbol_m = _symbols[offset + 3];
					final ArrayList _list_m = (ArrayList) _symbol_m.value;
					final String[] m = _list_m == null ? new String[0] : (String[]) _list_m.toArray(new String[_list_m.size()]);
					final Symbol e = _symbols[offset + 4];
					
			return new CSSImportNode(s, m, i.getStart(), e.getEnd());
			}
			case 13: // Media = MEDIA.m List.l LCURLY RCURLY.r
			{
					final Symbol m = _symbols[offset + 1];
					final Symbol _symbol_l = _symbols[offset + 2];
					final ArrayList _list_l = (ArrayList) _symbol_l.value;
					final String[] l = _list_l == null ? new String[0] : (String[]) _list_l.toArray(new String[_list_l.size()]);
					final Symbol r = _symbols[offset + 4];
					
			return new CSSMediaNode(l, m.getStart(), r.getEnd());
			}
			case 14: // Media = MEDIA.m List.l LCURLY Statements RCURLY.r
			{
					final Symbol m = _symbols[offset + 1];
					final Symbol _symbol_l = _symbols[offset + 2];
					final ArrayList _list_l = (ArrayList) _symbol_l.value;
					final String[] l = _list_l == null ? new String[0] : (String[]) _list_l.toArray(new String[_list_l.size()]);
					final Symbol r = _symbols[offset + 5];
					
			return new CSSMediaNode(l, m.getStart(), r.getEnd());
			}
			case 15: // Page = PAGE.p LCURLY RCURLY.r
			{
					final Symbol p = _symbols[offset + 1];
					final Symbol r = _symbols[offset + 3];
					
			return new CSSPageNode(p.getStart(), r.getEnd());
			}
			case 16: // Page = PAGE.p LCURLY Declarations.d RCURLY.r
			{
					final Symbol p = _symbols[offset + 1];
					final Symbol d = _symbols[offset + 3];
					final Symbol r = _symbols[offset + 4];
					
			return new CSSPageNode(d.value, p.getStart(), r.getEnd());
			}
			case 17: // Page = PAGE.p COLON IDENTIFIER.s LCURLY RCURLY.r
			{
					final Symbol p = _symbols[offset + 1];
					final Symbol _symbol_s = _symbols[offset + 3];
					final String s = (String) _symbol_s.value;
					final Symbol r = _symbols[offset + 5];
					
			return new CSSPageNode(s, p.getStart(), r.getEnd());
			}
			case 18: // Page = PAGE.p COLON IDENTIFIER.s LCURLY Declarations.d RCURLY.r
			{
					final Symbol p = _symbols[offset + 1];
					final Symbol _symbol_s = _symbols[offset + 3];
					final String s = (String) _symbol_s.value;
					final Symbol d = _symbols[offset + 5];
					final Symbol r = _symbols[offset + 6];
					
			return new CSSPageNode(s, d.value, p.getStart(), r.getEnd());
			}
			case 21: // Rule = Selectors.s LCURLY RCURLY.r
			{
					final Symbol _symbol_s = _symbols[offset + 1];
					final ArrayList _list_s = (ArrayList) _symbol_s.value;
					final beaver.Symbol[] s = _list_s == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_s.toArray(new beaver.Symbol[_list_s.size()]);
					final Symbol r = _symbols[offset + 3];
					
			return new CSSRuleNode(s, r.getEnd());
			}
			case 22: // Rule = Selectors.s LCURLY Declarations.d RCURLY.r
			{
					final Symbol _symbol_s = _symbols[offset + 1];
					final ArrayList _list_s = (ArrayList) _symbol_s.value;
					final beaver.Symbol[] s = _list_s == null ? new beaver.Symbol[0] : (beaver.Symbol[]) _list_s.toArray(new beaver.Symbol[_list_s.size()]);
					final Symbol d = _symbols[offset + 3];
					final Symbol r = _symbols[offset + 4];
					
			return new CSSRuleNode(s, d.value, r.getEnd());
			}
			case 24: // Function = LPAREN.l Expression.e RPAREN.r
			{
					final Symbol l = _symbols[offset + 1];
					final Symbol _symbol_e = _symbols[offset + 2];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol r = _symbols[offset + 3];
					
			return new CSSFunctionNode(e, l.getStart(), r.getEnd());
			}
			case 25: // List = List COMMA IDENTIFIER
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3].value); return _symbols[offset + 1];
			}
			case 26: // List = IDENTIFIER
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
			}
			case 28: // Declarations = Subdeclarations.l Declaration2.d
			{
					final Symbol _symbol_l = _symbols[offset + 1];
					final ArrayList _list_l = (ArrayList) _symbol_l.value;
					final CSSDeclarationNode[] l = _list_l == null ? new CSSDeclarationNode[0] : (CSSDeclarationNode[]) _list_l.toArray(new CSSDeclarationNode[_list_l.size()]);
					final Symbol _symbol_d = _symbols[offset + 2];
					final CSSDeclarationNode d = (CSSDeclarationNode) _symbol_d.value;
					
			_list_l.add(d); return _symbol_l;
			}
			case 30: // Subdeclarations = Subdeclarations Declaration
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
			}
			case 31: // Subdeclarations = Declaration
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
			}
			case 32: // Declaration = Declaration2.d SEMICOLON.s
			{
					final Symbol _symbol_d = _symbols[offset + 1];
					final CSSDeclarationNode d = (CSSDeclarationNode) _symbol_d.value;
					final Symbol s = _symbols[offset + 2];
					
			((CSSDeclarationNode) d).setHasSemicolon(s); return d;
			}
			case 33: // Declaration = SEMICOLON.s
			{
					final Symbol s = _symbols[offset + 1];
					
			return new CSSDeclarationNode(s);
			}
			case 34: // Declaration2 = Identifier.i COLON Expression.e
			{
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					
			return new CSSDeclarationNode(_symbol_i, e);
			}
			case 35: // Declaration2 = Identifier.i COLON Expression.e IMPORTANT.s
			{
					final Symbol _symbol_i = _symbols[offset + 1];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_e = _symbols[offset + 3];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol _symbol_s = _symbols[offset + 4];
					final String s = (String) _symbol_s.value;
					
			return new CSSDeclarationNode(_symbol_i, e, _symbol_s);
			}
			case 36: // Declaration2 = error.e
			{
					final Symbol e = _symbols[offset + 1];
					
			return new CSSErrorDeclarationNode(e.getStart(), e.getEnd());
			}
			case 37: // Expression = Expression.e Separator.s Term.t
			{
					final Symbol _symbol_e = _symbols[offset + 1];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol _symbol_s = _symbols[offset + 2];
					final String s = (String) _symbol_s.value;
					final Symbol _symbol_t = _symbols[offset + 3];
					final CSSExpressionNode t = (CSSExpressionNode) _symbol_t.value;
					
			return new CSSTermListNode(e, t, s);
			}
			case 38: // Expression = Expression.e Term.t
			{
					final Symbol _symbol_e = _symbols[offset + 1];
					final CSSExpressionNode e = (CSSExpressionNode) _symbol_e.value;
					final Symbol _symbol_t = _symbols[offset + 2];
					final CSSExpressionNode t = (CSSExpressionNode) _symbol_t.value;
					
			return new CSSTermListNode(e, t);
			}
			case 40: // Expression = error.e
			{
					final Symbol e = _symbols[offset + 1];
					
			return new CSSErrorExpressionNode(e.getStart(), e.getEnd());
			}
			case 41: // Term = Primitive.p
			{
					final Symbol _symbol_p = _symbols[offset + 1];
					final String p = (String) _symbol_p.value;
					
			return new CSSTermNode(_symbol_p);
			}
			case 43: // Selectors = Selectors Combinator Selector
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3]); return _symbols[offset + 1];
			}
			case 44: // Selectors = Selector
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
			}
			case 45: // Selector = Selector SimpleSelector
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2]); return _symbols[offset + 1];
			}
			case 46: // Selector = SimpleSelector
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
			}
			case 47: // SimpleSelector = TypeOrUniversalSelector.t AttributeSelectors.a
			{
					final Symbol _symbol_t = _symbols[offset + 1];
					final String t = (String) _symbol_t.value;
					final Symbol _symbol_a = _symbols[offset + 2];
					final ArrayList _list_a = (ArrayList) _symbol_a.value;
					final CSSAttributeSelectorNode[] a = _list_a == null ? new CSSAttributeSelectorNode[0] : (CSSAttributeSelectorNode[]) _list_a.toArray(new CSSAttributeSelectorNode[_list_a.size()]);
					
			return new CSSSimpleSelectorNode(_symbol_t, a);
			}
			case 48: // SimpleSelector = TypeOrUniversalSelector.t
			{
					final Symbol _symbol_t = _symbols[offset + 1];
					final String t = (String) _symbol_t.value;
					
			return new CSSSimpleSelectorNode(_symbol_t);
			}
			case 49: // SimpleSelector = AttributeSelectors.a
			{
					final Symbol _symbol_a = _symbols[offset + 1];
					final ArrayList _list_a = (ArrayList) _symbol_a.value;
					final CSSAttributeSelectorNode[] a = _list_a == null ? new CSSAttributeSelectorNode[0] : (CSSAttributeSelectorNode[]) _list_a.toArray(new CSSAttributeSelectorNode[_list_a.size()]);
					
			return new CSSSimpleSelectorNode(a);
			}
			case 50: // AttributeSelectors = AttributeSelectors AttributeSelector
			{
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 2].value); return _symbols[offset + 1];
			}
			case 51: // AttributeSelectors = AttributeSelector
			{
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1].value); return new Symbol(lst);
			}
			case 52: // AttributeSelector = CLASS.c
			{
					final Symbol _symbol_c = _symbols[offset + 1];
					final String c = (String) _symbol_c.value;
					
			return new CSSAttributeSelectorNode(c, _symbol_c.getStart(), _symbol_c.getEnd());
			}
			case 53: // AttributeSelector = COLON.c IDENTIFIER.i
			{
					final Symbol c = _symbols[offset + 1];
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					
			return new CSSAttributeSelectorNode(":" + i, c.getStart(), _symbol_i.getEnd());
			}
			case 54: // AttributeSelector = COLON.c Function.f
			{
					final Symbol c = _symbols[offset + 1];
					final Symbol _symbol_f = _symbols[offset + 2];
					final CSSExpressionNode f = (CSSExpressionNode) _symbol_f.value;
					
			return new CSSAttributeSelectorNode(f, c.getStart());
			}
			case 55: // AttributeSelector = COLOR.c
			{
					final Symbol _symbol_c = _symbols[offset + 1];
					final String c = (String) _symbol_c.value;
					
			return new CSSAttributeSelectorNode(c, _symbol_c.getStart(), _symbol_c.getEnd());
			}
			case 56: // AttributeSelector = HASH.h
			{
					final Symbol _symbol_h = _symbols[offset + 1];
					final String h = (String) _symbol_h.value;
					
			return new CSSAttributeSelectorNode(h, _symbol_h.getStart(), _symbol_h.getEnd());
			}
			case 57: // AttributeSelector = LBRACKET.l IDENTIFIER.i RBRACKET.r
			{
					final Symbol l = _symbols[offset + 1];
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					final Symbol r = _symbols[offset + 3];
					
			return new CSSAttributeSelectorNode("[" + i + "]", l.getStart(), r.getEnd());
			}
			case 58: // AttributeSelector = LBRACKET.l IDENTIFIER.i AttributeValueOperator.o IdentiferOrString.s RBRACKET.r
			{
					final Symbol l = _symbols[offset + 1];
					final Symbol _symbol_i = _symbols[offset + 2];
					final String i = (String) _symbol_i.value;
					final Symbol _symbol_o = _symbols[offset + 3];
					final String o = (String) _symbol_o.value;
					final Symbol _symbol_s = _symbols[offset + 4];
					final String s = (String) _symbol_s.value;
					final Symbol r = _symbols[offset + 5];
					
			return new CSSAttributeSelectorNode("[" + i + " " + o + " " + s + "]", l.getStart(), r.getEnd());
			}
			case 4: // Statement = CharSet
			case 5: // Statement = Import
			case 6: // Statement = Media
			case 7: // Statement = Page
			case 8: // Statement = AtRule
			case 9: // Statement = Rule
			case 23: // Rule = error
			case 27: // Declarations = Subdeclarations
			case 29: // Declarations = Declaration2
			case 39: // Expression = Term
			case 42: // Term = Function
			case 59: // ImportWord = STRING
			case 60: // ImportWord = URL
			case 61: // Identifier = IDENTIFIER
			case 62: // Identifier = PROPERTY
			case 63: // IdentiferOrString = IDENTIFIER
			case 64: // IdentiferOrString = STRING
			case 65: // Separator = SLASH
			case 66: // Separator = COMMA
			case 67: // Separator = PLUS
			case 68: // Separator = MINUS
			case 69: // Combinator = COMMA
			case 70: // Combinator = PLUS
			case 71: // Combinator = GREATER
			case 72: // Primitive = NUMBER
			case 73: // Primitive = PERCENTAGE
			case 74: // Primitive = LENGTH
			case 75: // Primitive = EMS
			case 76: // Primitive = EXS
			case 77: // Primitive = ANGLE
			case 78: // Primitive = TIME
			case 79: // Primitive = FREQUENCY
			case 80: // Primitive = STRING
			case 81: // Primitive = IDENTIFIER
			case 82: // Primitive = URL
			case 83: // Primitive = COLOR
			case 84: // TypeOrUniversalSelector = IDENTIFIER
			case 85: // TypeOrUniversalSelector = STAR
			case 86: // TypeOrUniversalSelector = SELECTOR
			case 87: // AttributeValueOperator = EQUAL
			case 88: // AttributeValueOperator = INCLUDES
			case 89: // AttributeValueOperator = DASHMATCH
			{
				return _symbols[offset + 1];
			}
			case 19: // AtRule = AT_RULE STRING SEMICOLON
			{
				return _symbols[offset + 3];
			}
			case 20: // AtRule = AT_RULE STRING LCURLY RCURLY
			{
				return _symbols[offset + 4];
			}
			default:
				throw new IllegalArgumentException("unknown production #" + rule_num);
		}
	}
}
