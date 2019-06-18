require "lualdap"



ld = assert (lualdap.open_simple { uri = "ldap://ldap.server",

 who = "mydn=syafiq,ou=people,dc=ldap,dc=world",

 password = "pass123" })



for dn, attribs in ld:search { base = "ou=people,dc=ldap,dc=world" } do

 io.write (string.format ("\t[%s]\n", dn))

 for name, values in pairs (attribs) do

  io.write ("["..name.."] : ")

  if type (values) == "string" then

   io.write (values)

  elseif type (values) == "table" then

   local n = table.getn(values)

   for i = 1, (n-1) do

    io.write (values[i]..",")

   end

   io.write (values[n])

  end

  io.write ("\n")

 end

end

ld:add ("mydn=newuser,ou=people,dc=ldap,dc=world", {

 objectClass = { "", "", },

 mydn = "newuser",

 abc = "qwerty",

 tel = { "123456758", "98765432", },

 givenName = "New User",

})()



ld:modify {"mydn=newuser,ou=people,dc=ldp,dc=world",

 { '=', givenName = "New", cn = "New", sn = "User", },

 { '+', o = { "University", "College", },

  mail = "newuser@university.edu", },

 { '-', abc = true, tel = "123456758", },

 { '+', tel = "13579113", },

}()



ld:delete ("mydn=newuser,ou=people,dc=ldp,dc=world")()
