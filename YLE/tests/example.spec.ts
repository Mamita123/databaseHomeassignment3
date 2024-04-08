import { test, expect } from '@playwright/test';

test('has title', async ({ page }) => {
  await page.goto('https://areena.yle.fi/lapset/1-66607308');

  
  // Expect a title "to contain" a substring.
  await expect(page).toHaveTitle(/"Herkkuja tarjolla! | Nalle - viitotaan ja leikitÃ¤Ã¤n | Lasten Areena | yle.fi"/);
});

