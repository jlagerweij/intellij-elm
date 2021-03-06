package org.elm.lang.core.psi.elements

import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil
import org.elm.lang.core.psi.ElmNamedElement
import org.elm.lang.core.psi.ElmPsiElementImpl


/**
 * A pattern-matching branch in a case-of expression.
 */
class ElmCaseOfBranch(node: ASTNode) : ElmPsiElementImpl(node) {

    /**
     * The pattern on the left-hand-side of the branch.
     *
     * In a well-formed program, this will be non-null.
     */
    val pattern: ElmPattern?
        get() = findChildByClass(ElmPattern::class.java)

    /**
     * The body expression on the right-hand-side of the branch.
     *
     * In a well-formed program, this will be non-null.
     */
    val expression: ElmExpression?
        get() = findChildByClass(ElmExpression::class.java)


    /**
     * Named elements introduced by pattern destructuring on the left-hand side of the branch.
     */
    val destructuredNames: List<ElmNamedElement>
        get() = listOf(PsiTreeUtil.collectElementsOfType(this, ElmLowerPattern::class.java),
                       PsiTreeUtil.collectElementsOfType(this, ElmPatternAs::class.java)
                      ).flatten()
}
