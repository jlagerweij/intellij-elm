package org.elm.lang.core.resolve


/**
 * Tests related to the namespace of Types
 */
class ElmTypeResolveTest: ElmResolveTestBase() {


    fun `test union type ref`() = checkByCode(
"""
type Page = Home
     --X

title : Page -> String
        --^
""")


    fun `test union type ref from module exposing list`() = checkByCode(
"""
module Main exposing (Page)
                      --^

type Page = Home
     --X
""".trimStart()) // TODO [kl] remove `trimStart` once I fix the parse bug when module is on 2nd line


    fun `test union constructor ref`() = checkByCode(
"""
type Page = Home
            --X

defaultPage = Home
              --^
""")


    fun `test union constructor pattern matching`() = checkByCode(
"""
type Page = Home
            --X

title page =
    case page of
        Home -> "home"
        --^
""")


    fun `test union constructor ref from module exposing list`() = checkByCode(
"""
module Main exposing (Page(Home))
                           --^

type Page = Home
            --X
""".trimStart())


    fun `test type alias ref from module exposing list`() = checkByCode(
"""
module Main exposing (Person)
                      --^
type alias Person = { name : String, age: Int }
           --X
""".trimStart())


    fun `test type alias ref in type annotation`() = checkByCode(
"""
type alias Person = { name : String, age: Int }
           --X

personToString : Person -> String
                 --^
""")


    fun `test type alias record constructor ref`() = checkByCode(
"""
type alias Person = { name : String, age: Int }
           --X

defaultPerson = Person "George" 42
                --^
""")


    fun `test parametric union type ref `() = checkByCode(
"""
type Page a = Home a
     --X

title : Page a -> String
        --^
""")


    fun `test parametric type alias ref `() = checkByCode(
"""
type alias Person a = { name : String, extra : a }
           --X

title : Person a -> String
        --^
""")



    fun `test union constructor ref should not resolve to a record constructor`() = checkByCode(
"""
type alias User = { name : String, age : Int }

foo user =
    case user of
        User -> "foo"
        --^unresolved
""")

}