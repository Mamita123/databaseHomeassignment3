import { test, expect } from '@playwright/test';



/*test('test', async ({ page }) => {

 
    await page.goto('https://areena.yle.fi/lapset/1-66607308');
    await page.locator('#mute').click();
    await page.locator('#volume-control').fill('0.53');
    await page.locator('#mute').click();
    await page.locator('#volume-control').fill('0.38');
    await page.locator('#mute').click();
  });*/

  test('test', async ({ page }) => {
    await page.goto('https://areena.yle.fi/lapset/1-66607308');

    // Perform actions
    await page.locator('#mute').click();
    await page.locator('#volume-control').fill('0.53');
    await page.locator('#mute').click();
    await page.locator('#volume-control').fill('0.38');
    await page.locator('#mute').click();

    // Expectation
  const volumeValue = await page.locator('#volume-control').getAttribute('value');
  console.log('Volume Value:', volumeValue);
});


