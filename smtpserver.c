#include <stdio.h>
#include <io.h>




#include <stdio.h>



//#define LINUX /* define this if you are on linux   */

#ifdef LINUX

#       include <netdb.h>         /* gethostbyname  */

#       include <netinet/in.h>    /* htons          */

#       include <sys/socket.h>

#endif



static void sendmail_write(

                const int  sock,

                const char *str,

                const char *arg

            ) {

    char buf[4096];



    if (arg != NULL)

        snprintf(buf, sizeof(buf), str, arg);        

    else

        snprintf(buf, sizeof(buf), str);

    

    send(sock, buf, strlen(buf), 0);    

}


static int sendmail(

                const char *from,

                const char *to,

                const char *subject,

                const char *body,

                const char *hostname,

                const int   port

            ) {

    struct hostent *host;   

    struct sockaddr_in saddr_in;

    int sock = 0;



#ifdef WIN32

	WSADATA wsaData;

	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {

		return -1;

	}

#endif

sock = socket(AF_INET, SOCK_STREAM, 0);

	host = gethostbyname(hostname);

	

	saddr_in.sin_family      = AF_INET;

	saddr_in.sin_port        = htons((u_short)port);

	saddr_in.sin_addr.s_addr = 0;



	memcpy((char*)&(saddr_in.sin_addr), host->h_addr, 
host->h_length);



	if (connect(sock, (struct sockaddr*)&saddr_in, sizeof(saddr_in)) 
== -1) {

		return -2;

	}

    

    sendmail_write(sock, "HELO %s\n",       from);    // greeting

    sendmail_write(sock, "MAIL FROM: %s\n", from);    // from

    sendmail_write(sock, "RCPT TO: %s\n",   to);      // to

    sendmail_write(sock, "DATA\n",          NULL);    // begin data



    // next comes mail headers

    sendmail_write(sock, "From: %s\n",      from);

    sendmail_write(sock, "To: %s\n",        to);

    sendmail_write(sock, "Subject: %s\n",   subject);



    sendmail_write(sock, "\n",              NULL);

sendmail_write(sock, "%s\n",            body);    // data



    sendmail_write(sock, ".\n",             NULL);    // end data

    sendmail_write(sock, "QUIT\n",          NULL);    // terminate



    close(sock);



    return 0;

}





int main(int argc, char *argv[]) {

    

    int ret = sendmail(

        "from@from-host.org",   /* from     */

        "to@to-host.org",       /* to       */

        "Subject",              /* subject  */

        "body",                 /* body     */

        "hostname",             /* hostname */

        25                      /* port     */

    );

 if (ret != 0)

        fprintf(stderr, "Failed to send mail (code: %i).\n", ret);

    else

        fprintf(stdout, "Mail successfully sent.\n");



    return ret;

}
