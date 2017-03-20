#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main() 
{
int fd=open("data1",O_RDWR|O_CREAT,0660);
char buffer[256];
struct sockaddr_in addr = {0};
size_t addrlen, n;

int sockfd = socket(AF_INET, SOCK_DGRAM, 0);
addr.sin_family = AF_INET;
addr.sin_port = htons(19090);
addr.sin_addr.s_addr = INADDR_ANY;
bind(sockfd, (struct sockaddr*)&addr, sizeof(addr));
addrlen = sizeof(addr);
write(fd,"TST\n",5);
fsync(fd);
printf("mylog: fd %d sock %d\n",fd,sockfd);
while(1)
{
	n = recvfrom(sockfd, (void*)buffer, 100, 0,(struct sockaddr*)&addr, (unsigned int *) &addrlen);
	buffer[n] = '\n';
	write(fd,buffer,n+1);
	buffer[n] = 0;

	printf("mylog:[%d]= %s\n",n,buffer);

}
close(fd);
return 0;
}
