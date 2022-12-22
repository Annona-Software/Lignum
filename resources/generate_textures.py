from PIL import Image, ImageDraw, ImageEnhance, ImageOps
from PIL.Image import Transpose

import colorsys
from constants import *

path = './src/main/resources/assets/lignum/textures/'
mc_path = './src/main/resources/assets/minecraft/textures/'
templates = './resources/texture_templates/'


def overlay_image(front_file_dir, back_file_dir, result_dir):
    foreground = Image.open(front_file_dir + '.png')
    background = Image.open(back_file_dir + '.png').convert('RGBA')
    background.paste(foreground, (0, 0), foreground.convert('RGBA'))
    background.save(result_dir + '.png')

def create_logs(wood: str, plank_color):
    log = Image.open(templates + 'log.png')
    face = Image.open(templates + 'log_face.png')
    log_dark = Image.open(templates + 'log_dark_face.png')
    actual_log = Image.open(path + 'item/wood/log/%s.png' % wood).convert('RGBA')
    wood_item = Image.alpha_composite(actual_log, put_on_all_pixels(face, actual_log.getpixel((4, 4))))
    wood_item.save(path + 'item/wood/wood/%s.png' % wood)

    stripped_log_item = put_on_all_pixels(log, plank_color)
    stripped_log_item.save(path + 'item/wood/stripped_log/%s.png' % wood)
    stripped_wood_item = put_on_all_pixels(log_dark, plank_color)
    stripped_wood_item.save(path + 'item/wood/stripped_wood/%s.png' % wood)

def get_wood_colors(wood_path: str):
    wood = Image.open(path + 'block/wood/%s.png' % wood_path)
    return wood.getpixel((0, 0))

def easy_colorize(color, from_path, to_path, saturation: float = 1):
    img = Image.open(from_path + '.png')
    new_image = put_on_all_pixels(img, color)
    if saturation != 1:
        new_image = ImageEnhance.Color(new_image).enhance(saturation)
    new_image.save(to_path + '.png')

def put_on_all_pixels(img: Image, color) -> Image:
    if isinstance(color, int):
        color = (color, color, color, 255)
    img = img.convert('RGBA')
    _, _, _, alpha = img.split()
    img = img.convert('HSV')
    hue, sat, _ = colorsys.rgb_to_hsv(color[0], color[1], color[2])
    for x in range(0, img.width):
        for y in range(0, img.height):
            dat = img.getpixel((x, y))
            tup = (int(hue * 255), int(sat * 255), int(dat[2]))
            img.putpixel((x, y), tup)
    img = img.convert('RGBA')
    img.putalpha(alpha)
    return img

def main():
    for wood in WOODS.keys():
        overlay_image(templates + 'log_top/%s' % wood, path + 'block/wood/log/%s' % wood, path + 'block/wood/log_top/%s' % wood)
        overlay_image(templates + 'log_top/%s' % wood, path + 'block/wood/stripped_log/%s' % wood, path + 'block/wood/stripped_log_top/%s' % wood)
        plank_color = get_wood_colors('planks/%s' % wood)
        log_color = get_wood_colors('log/%s' % wood)
        for item in ('lumber'):
            easy_colorize(plank_color, templates + '/%s' % item, path + 'item/wood/%s/%s' % (item, wood))
        create_logs(wood, plank_color)

if __name__ == '__main__':
    main()