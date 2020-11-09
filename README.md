# SI_MP3_MOM

### Made by:
**Adam Saidane - as410**
**Emil Valbak Hermansen - eh108**
**Sebastian Lundsgaard-Larsen - sl281**  
  
  
[Assignment description](https://datsoftlyngby.github.io/soft2020fall/resources/135fdeae-A8-MOM.pdf)

## Project Description
We chose assignment 1, but with a few differences.
We make topics that users can subscribe to. Inside these topics we make offers that users can accept/decline. Logs are then made with username and what offers they have accepted.



## How to run 🐒
1. Run the RabbitProducer application and send messages to the different topics.
    * RabbitProducer asks which topic you want to make an offer in. Write the number of the topic to chose. 
    * Then write the name of an offer you want to make.

2. Run the RabbitConsumer application and subscribe to the topics you want to get ads from.
    * The application asks for your username. 
    * Then which topics you want to subscribe to with a yes/no. (Remember to subscribe to the topic, that you made an offer in)
    * Then asks if you want to accept/decline a current offer if you subscribe to the topic that includes the offer. 



Orders you have accepted are saved in the `src/main/java/resource/orders` folder.
