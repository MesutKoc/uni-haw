/**
  ******************************************************************************
  * @file    	main.c 
  * @author  	Alfred Lohmann
  *        	  	HAW-Hamburg
  *          	Labor für technische Informatik
  *          	Berliner Tor  7
  *          	D-20099 Hamburg
  * @version V1.0
  * @date    23.05.2013
  * @brief   Main program body
  *******************************************************************
  *
	*
	* @brief		main.c für ADC-Aufgabe
	* @history	
	*						Cnz 2014-4-14 	Anpassung für RMPP SS14
	*						Beh 2013-7-29	erste Version
	*
	*/


/* Includes ------------------------------------------------------------------*/
#include "main.h"
#include <stdio.h>
#include <string.h>
#include "TI_Lib.h"
#include "tft.h"
#include "stm32f4xx.h"
#include "stm32f4xx_gpio.h"
#include "stm32f4xx_rcc.h"
#include "TI_Board_ADC.h"



/* Private typedef -----------------------------------------------------------*/
/* Private define ------------------------------------------------------------*/
/* Private macro -------------------------------------------------------------*/
/* Private variables ---------------------------------------------------------*/
/* Private function prototypes -----------------------------------------------*/
extern void mainASM(void);

/* Private functions ---------------------------------------------------------*/

/**
  * @brief  Main program
  * @param  None
  * @retval None
  */
int main(void)
{
    
  Init_TI_Board();
	ADC3_CH7_DMA_Config(); // Initialisierung ADC Nr3 Kanal 7
 
	mainASM();

}

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
