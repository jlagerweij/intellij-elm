package org.elm.lang.core.parser.manual

import com.intellij.lang.ASTNode
import org.elm.lang.core.psi.ElmTypes
import org.elm.lang.core.psi.elements.*

object ElmManualPsiElementFactory {

    fun createElement(node: ASTNode) =
            when (node.elementType) {
                ElmTypes.UPPER_CASE_QID ->              ElmUpperCaseQID(node)
                ElmTypes.FIELD_ACCESS ->                ElmFieldAccess(node)
                ElmTypes.VALUE_QID ->                   ElmValueQID(node)
                ElmTypes.FIELD_ACCESSOR_FUNCTION ->     ElmFieldAccessorFunction(node)
                ElmTypes.EFFECT ->                      ElmEffect(node)
                else ->                                 null
            }
}
