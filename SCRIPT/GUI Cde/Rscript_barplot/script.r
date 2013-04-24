# author:surabhi_pandey
# Goal: to read all o/p file and generate group bar chart plot
#please change the location of file as in your system its dummy.csv located @ /home/regulus/workspace/OSproj/lists/dummy.csv location

counts<-read.csv(file="/home/regulus/workspace/OSproj/lists/dummy.csv",head=FALSE,sep=",")
col1<-counts$V2
col2<-counts$V1
col3<-counts$V3
require(ggplot2)
require(gridExtra)
ggplot(counts, aes(x=col1, y=col3, fill=col2)) +geom_bar(position=position_dodge(),stat="identity",width=.5) + ylab("Number Of Planets Identified")+xlab("NASA VS SVM")+ggtitle("Comparison of Planets detected by Nasa and SVM")+scale_y_continuous(limits = c(0,50))+theme(plot.margin= unit(rep(.5, 400), "lines"))
ggsave("ggplot1.png")

