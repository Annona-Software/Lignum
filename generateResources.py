#!/bin/env python3
"""
This file generates a whole list of resources for the Woodworking TFC mod.
Any resource files generated by this script should set a root JSON tag:
    "__comment": "Generated by generateResources.py function: model"

You should set this script up to run automatically whenever you launch the game, and make sure it's run before you commit.
For IntelliJ instructions, see README.md.
"""

import json
import os
import time
import zipfile


def zipfolder(zip_name, target_dir):
    zipobj = zipfile.ZipFile(zip_name, 'w', zipfile.ZIP_DEFLATED)
    rootlen = len(target_dir) + 1
    for base, dirs, files in os.walk(target_dir):
        for file in files:
            fn = os.path.join(base, file)
            zipobj.write(fn, fn[rootlen:])


if not os.path.isdir('assets_backups'):
    os.mkdir('assets_backups')
    with open('assets_backups/.gitignore', 'w') as f:
        print(
            '# This folder does not belong on git. Not even as an empty folder, so we ignore everything, incl. this file.',
            file=f)
        print('*', file=f)

zipfolder('assets_backups/{}.zip'.format(int(time.time())), 'src/main/resources/assets/woodworking')

os.chdir('src/main/resources/assets/woodworking/')

ROCK_TYPES = [
    'granite',
    'diorite',
    'gabbro',
    'shale',
    'claystone',
    'rocksalt',
    'limestone',
    'conglomerate',
    'dolomite',
    'chert',
    'chalk',
    'rhyolite',
    'basalt',
    'andesite',
    'dacite',
    'quartzite',
    'slate',
    'phyllite',
    'schist',
    'gneiss',
    'marble',
]
ROCK_CATEGORIES = [
    'sedimentary',
    'metamorphic',
    'igneous_intrusive',
    'igneous_extrusive',
]
FULLBLOCK_TYPES = [
    'raw',
    'smooth',
    'cobble',
    'bricks',
    'sand',
    'gravel',
    'dirt',
    'clay',
]
GRASS_TYPES = [
    'grass',
    'dry_grass',
]
ORE_TYPES = {
    'native_copper': True,
    'native_gold': True,
    'native_platinum': True,
    'hematite': True,
    'native_silver': True,
    'cassiterite': True,
    'galena': True,
    'bismuthinite': True,
    'garnierite': True,
    'malachite': True,
    'magnetite': True,
    'limonite': True,
    'sphalerite': True,
    'tetrahedrite': True,
    'bituminous_coal': False,
    'lignite': False,
    'kaolinite': False,
    'gypsum': False,
    'satinspar': False,
    'selenite': False,
    'graphite': False,
    'kimberlite': False,
    'petrified_wood': False,
    'sulfur': False,
    'jet': False,
    'microcline': False,
    'pitchblende': False,
    'cinnabar': False,
    'cryolite': False,
    'saltpeter': False,
    'serpentine': False,
    'sylvite': False,
    'borax': False,
    'olivine': False,
    'lapis_lazuli': False,
}
POWDERS = [
    'flux',
    'coke',
    'kaolinite',
    'graphite',
    'sulfur',
    'saltpeter',
    'hematite',
    'lapis_lazuli',
    'limonite',
    'malachite',
    'salt',
    'fertilizer',
]
WOOD_TYPES = [
    'ash',
    'aspen',
    'birch',
    'chestnut',
    'douglas_fir',
    'hickory',
    'maple',
    'oak',
    'pine',
    'sequoia',
    'spruce',
    'sycamore',
    'white_cedar',
    'willow',
    'kapok',
    'acacia',
    'rosewood',
    'blackwood',
    'palm',
]
BERRY_TYPES = {
    'blackberry': 'large',
    'blueberry': 'large',
    'bunch_berry': 'small',
    'cloud_berry': 'medium',
    'cranberry': 'medium',
    'elderberry': 'large',
    'gooseberry': 'medium',
    'raspberry': 'large',
    'snow_berry': 'small',
    'strawberry': 'small',
    'wintergreen_berry': 'small',
}
GEM_TYPES = [
    'agate',
    'amethyst',
    'beryl',
    'diamond',
    'emerald',
    'garnet',
    'jade',
    'jasper',
    'opal',
    'ruby',
    'sapphire',
    'topaz',
    'tourmaline',
]
GEM_GRADES = [
    'normal',
    'flawed',
    'flawless',
    'chipped',
    'exquisite',
]
METAL_TYPES = {
    'bismuth': False,
    'bismuth_bronze': True,
    'black_bronze': True,
    'brass': False,
    'bronze': True,
    'copper': True,
    'gold': False,
    'lead': False,
    'nickel': False,
    'rose_gold': False,
    'silver': False,
    'tin': False,
    'zinc': False,
    'sterling_silver': False,
    'wrought_iron': True,
    'pig_iron': False,
    'steel': True,
    'platinum': False,
    'black_steel': True,
    'blue_steel': True,
    'red_steel': True,
}  # + unknown
METAL_ITEMS = {
    # 'unshaped': False, Special
    'ingot': False,
    'double_ingot': False,
    'scrap': False,
    'dust': False,
    'nugget': False,
    'sheet': False,
    'double_sheet': False,
    'anvil': True,
    'tuyere': True,
    'lamp': False,
    'pick': True,
    'pick_head': True,
    'shovel': True,
    'shovel_head': True,
    'axe': True,
    'axe_head': True,
    'hoe': True,
    'hoe_head': True,
    'chisel': True,
    'chisel_head': True,
    'sword': True,
    'sword_blade': True,
    'mace': True,
    'mace_head': True,
    'saw': True,
    'saw_blade': True,
    'javelin': True,
    'javelin_head': True,
    'hammer': True,
    'hammer_head': True,
    'propick': True,
    'propick_head': True,
    'knife': True,
    'knife_blade': True,
    'scythe': True,
    'scythe_blade': True,
    'shears': True,
    'unfinished_chestplate': True,
    'chestplate': True,
    'unfinished_greaves': True,
    'greaves': True,
    'unfinished_boots': True,
    'boots': True,
    'unfinished_helmet': True,
    'helmet': True,
}
STEEL = {
    'steel',
    'blue_steel',
    'red_steel',
    'black_steel',
}
TOOLS = [
    'pick', 'propick', 'shovel', 'axe', 'hoe', 'chisel', 'sword', 'mace', 'saw', 'shears', 'javelin', 'hammer', 'knife',
    'scythe'
]
FLUIDS = {
    'salt_water': 'salt_water',
    'fresh_water': 'fresh_water',
    'hot_water': 'hot_water',
    'finite_salt_water': 'salt_water',
    'finite_fresh_water': 'fresh_water',
    'finite_hot_water': 'hot_water',
    'rum': 'rum',
    'beer': 'beer',
    'whiskey': 'whiskey',
    'rye_whiskey': 'rye_whiskey',
    'corn_whiskey': 'corn_whiskey',
    'sake': 'sake',
    'vodka': 'vodka',
    'cider': 'cider',
    'vinegar': 'vinegar',
    'brine': 'brine',
    'milk': 'milk',
    'olive_oil': 'olive_oil',
    'tannin': 'tannin',
    'limewater': 'limewater',
    'milk_curdled': 'milk_curdled',
    'milk_vinegar': 'milk_vinegar',
}

# all crops
CROPS = {
    'barley': {'model': 'crop', 'texture': 'crop', 'stages': 8},
    'maize': {'model': 'tfc:crop_tall', 'texture': 'crop', 'stages': 6},
    'oat': {'model': 'crop', 'texture': 'crop', 'stages': 8},
    'rice': {'model': 'crop', 'texture': 'crop', 'stages': 8},
    'rye': {'model': 'crop', 'texture': 'crop', 'stages': 8},
    'wheat': {'model': 'crop', 'texture': 'crop', 'stages': 8},
    'beet': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'cabbage': {'model': 'cross', 'texture': 'cross', 'stages': 6},
    'carrot': {'model': 'cross', 'texture': 'cross', 'stages': 5},
    'garlic': {'model': 'cross', 'texture': 'cross', 'stages': 5},
    'green_bean': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'onion': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'potato': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'soybean': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'sugarcane': {'model': 'tfc:crop_tall', 'texture': 'crop', 'stages': 8},
    'red_bell_pepper': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'tomato': {'model': 'tfc:large_cross', 'texture': 'cross', 'stages': 8},
    'yellow_bell_pepper': {'model': 'cross', 'texture': 'cross', 'stages': 7},
    'jute': {'model': 'tfc:crop_tall', 'texture': 'crop', 'stages': 6},
    'pumpkin': {'model': 'crop', 'texture': 'crop', 'stages': 8},
    'squash': {'model': 'cross', 'texture': 'cross', 'stages': 8},
    'melon': {'model': 'crop', 'texture': 'crop', 'stages': 8}
}

FOODS = [
    'banana',
    'blackberry',
    'blueberry',
    'bunch_berry',
    'cherry',
    'cloud_berry',
    'cranberry',
    'elderberry',
    'gooseberry',
    'green_apple',
    'lemon',
    'olive',
    'orange',
    'peach',
    'plum',
    'raspberry',
    'red_apple',
    'snow_berry',
    'strawberry',
    'wintergreen_berry',
    'barley',
    'barley_grain',
    'barley_flour',
    'barley_dough',
    'barley_bread',
    'maize',
    'cornbread',
    'cornmeal_flour',
    'cornmeal_dough',
    'oat',
    'oat_grain',
    'oat_flour',
    'oat_dough',
    'oat_bread',
    'rice',
    'rice_grain',
    'rice_flour',
    'rice_dough',
    'rice_bread',
    'rye',
    'rye_grain',
    'rye_flour',
    'rye_dough',
    'rye_bread',
    'wheat',
    'wheat_grain',
    'wheat_flour',
    'wheat_dough',
    'wheat_bread',
    'beet',
    'cabbage',
    'carrot',
    'garlic',
    'green_bean',
    'green_bell_pepper',
    'onion',
    'potato',
    'red_bell_pepper',
    'seaweed',
    'soybean',
    'squash',
    'tomato',
    'yellow_bell_pepper',
    'cheese',
    'cooked_egg',
    'sugarcane',
    'beef',
    'pork',
    'chicken',
    'mutton',
    'fish',
    'bear',
    'calamari',
    'horse_meat',
    'pheasant',
    'venison',
    'rabbit',
    'wolf',
    'cooked_beef',
    'cooked_pork',
    'cooked_chicken',
    'cooked_mutton',
    'cooked_fish',
    'cooked_bear',
    'cooked_calamari',
    'cooked_horse_meat',
    'cooked_pheasant',
    'cooked_wolf',
    'cooked_venison',
    'cooked_rabbit'
]

def del_none(d):
    """
    https://stackoverflow.com/a/4256027/4355781
    Modifies input!
    """
    for key, value in list(d.items()):
        if value is None:
            del d[key]
        elif isinstance(value, dict):
            del_none(value)
    return d


def blockstate(filename_parts, model, textures, variants=None, uvlock=None):
    """
    Magic.
    :param filename_parts: Iterable of strings.
    :param model: String or None
    :param textures: Dict of <string>:<string> OR <iterable of strings>:<string>
    :param variants: Dict of <string>:<variant> OR "normal":None (to disable the normal default)
    """
    _variants = {
        'normal': [{}]
    }
    if variants:
        _variants.update(variants)

    # Unpack any tuple keys to simple string->string pairs
    _textures = {}
    for key, val in textures.items():
        if isinstance(key, str):
            _textures[key] = val
        else:
            for x in key:
                _textures[x] = val

    p = os.path.join('blockstates', *filename_parts) + '.json'
    os.makedirs(os.path.dirname(p), exist_ok=True)
    with open(p, 'w') as file:
        json.dump(del_none({
            '__comment': 'Generated by generateResources.py function: blockstate',
            'forge_marker': 1,
            'defaults': {
                'model': model,
                'textures': _textures,
                'uvlock': True if uvlock else None
            },
            'variants': _variants
        }), file, indent=2)


def cube_all(filename_parts, texture, variants=None, model='cube_all'):
    blockstate(filename_parts, model, textures={'all': texture}, variants=variants)


def model(filename_parts, parent, textures):
    p = os.path.join('models', *filename_parts) + '.json'
    os.makedirs(os.path.dirname(p), exist_ok=True)
    with open(p, 'w') as file:
        json.dump(del_none({
            '__comment': 'Generated by generateResources.py function: model',
            'parent': parent,
            'textures': textures,
        }), file, indent=2)


def item(filename_parts, *layers, parent='item/generated'):
    model(('item', *filename_parts), parent,
          None if len(layers) == 0 else {'layer%d' % i: v for i, v in enumerate(layers)})


#   ____  _            _        _        _
#  |  _ \| |          | |      | |      | |
#  | |_) | | ___   ___| | _____| |_ __ _| |_ ___  ___
#  |  _ <| |/ _ \ / __| |/ / __| __/ _` | __/ _ \/ __|
#  | |_) | | (_) | (__|   <\__ \ || (_| | ||  __/\__ \
#  |____/|_|\___/ \___|_|\_\___/\__\__,_|\__\___||___/
#
# BLOCKSTATES
"""
# FLUIDS
for name, fluid in FLUIDS.items():
    blockstate(('fluid', name), 'forge:fluid', {}, {
        'normal': {
            'transform': "forge:default-item",
            'custom': {
                'fluid': fluid
            }
        }
    })

# METAL FLUIDS
for name in METAL_TYPES.keys():
    blockstate(('fluid', name), 'forge:fluid', {}, {
        'normal': {
            'transform': "forge:default-item",
            'custom': {
                'fluid': name
            }
        }
    })
for name in STEEL:
    for type in ['weak', 'high_carbon']:
        if name == 'black_steel' and type == 'weak':
            continue
        blockstate(('fluid', type + "_" + name), 'forge:fluid', {}, {
            'normal': {
                'transform': "forge:default-item",
                'custom': {
                    'fluid': type + "_" + name
                }
            }
        })
blockstate(('fluid', 'unknown'), 'forge:fluid', {}, {
    'normal': {
        'transform': "forge:default-item",
        'custom': {
            'fluid': 'unknown'
        }
    }
})

# ANVILS
for key in METAL_TYPES:
    if METAL_TYPES[key]:
        blockstate(('anvil', key), 'tfc:anvil', textures={
            ('all', 'particle'): 'tfc:blocks/metal/%s' % key
        }, variants={
            'axis': {
                'north': {'y': 180},
                'east': {'y': 270},
                'south': {},
                'west': {'y': 90}
            }
        })

# METAL SHEETS
for key in METAL_TYPES:
    blockstate(('sheet', key), 'tfc:empty', textures={
        ('all', 'particle'): 'tfc:blocks/metal/%s' % key
    }, variants={
        'normal': None,
        "north": {"true": {"submodel": {"north": {"model": "tfc:sheet", "x": 90}}}, "false": {}},
        "south": {"true": {"submodel": {"south": {"model": "tfc:sheet", "y": 180, "x": 90}}}, "false": {}},
        "east": {"true": {"submodel": {"east": {"model": "tfc:sheet", "y": 90, "x": 90}}}, "false": {}},
        "west": {"true": {"submodel": {"west": {"model": "tfc:sheet", "y": 270, "x": 90}}}, "false": {}},
        "up": {"true": {"submodel": {"up": {"model": "tfc:sheet"}}}, "false": {}},
        "down": {"true": {"submodel": {"down": {"model": "tfc:sheet", "x": 180}}}, "false": {}}
    })

# ROCK STUFF
for rock_type in ROCK_TYPES:
    # FULL BLOCKS
    for block_type in FULLBLOCK_TYPES:
        cube_all((block_type, rock_type), 'tfc:blocks/stonetypes/%s/%s' % (block_type, rock_type))

    # ORES
    for block_type in ORE_TYPES:
        blockstate(('ore', block_type, rock_type), 'tfc:ore', textures={
            ('all', 'particle'): 'tfc:blocks/stonetypes/raw/%s' % rock_type,
            'overlay': 'tfc:blocks/ores/%s' % block_type,
        })

    # GRASS
    for block_type in GRASS_TYPES:
        blockstate((block_type, rock_type), 'tfc:grass', textures={
            ('all', 'particle'): 'tfc:blocks/stonetypes/dirt/%s' % rock_type,
            'particle': 'tfc:blocks/stonetypes/dirt/%s' % rock_type,
            'top': 'tfc:blocks/%s_top' % block_type,
            ('north', 'south', 'east', 'west'): 'tfc:blocks/%s_side' % block_type,
        }, variants={
            side: {
                'true': {'textures': {side: 'tfc:blocks/%s_top' % block_type}},
                'false': {}
            } for side in ['north', 'south', 'east', 'west']
        })

    # CLAY GRASS
    blockstate(('clay_grass', rock_type), 'tfc:grass', textures={
        ('all', 'particle'): 'tfc:blocks/stonetypes/clay/%s' % rock_type,
        'top': 'tfc:blocks/grass_top',
        ('north', 'south', 'east', 'west'): 'tfc:blocks/grass_side',
    }, variants={
        side: {
            'true': {'textures': {side: 'tfc:blocks/grass_top'}},
            'false': {}
        } for side in ['north', 'south', 'east', 'west']
    })

    # PATH
    blockstate(('path', rock_type), 'grass_path', textures={
        ('bottom', 'particle'): 'tfc:blocks/stonetypes/dirt/%s' % rock_type,
        'top': 'tfc:blocks/stonetypes/path/top/%s' % rock_type,
        'side': 'tfc:blocks/stonetypes/path/side/%s' % rock_type,
    })

    # FARMLAND
    blockstate(('farmland', rock_type), 'tfc:farmland', textures={
        ('dirt', 'particle'): 'tfc:blocks/stonetypes/dirt/%s' % rock_type,
        'top': 'tfc:blocks/stonetypes/farmland/%s' % rock_type,
    })

    # WALLS (smooth, cobble, bricks)
    for block_type in ['smooth', 'cobble', 'bricks']:
        blockstate(('wall', block_type, rock_type), 'tfc:empty', textures={
            ('wall', 'particle'): 'tfc:blocks/stonetypes/%s/%s' % (block_type, rock_type),
        }, variants={
            'normal': None,
            'inventory': {'model': 'wall_inventory'},
            'north': {'true': {'submodel': 'wall_side'}, 'false': {}},
            'east': {'true': {'submodel': 'wall_side', 'y': 90}, 'false': {}},
            'south': {'true': {'submodel': 'wall_side', 'y': 180}, 'false': {}},
            'west': {'true': {'submodel': 'wall_side', 'y': 270}, 'false': {}},
            'up': {'true': {'submodel': 'wall_post', 'y': 270}, 'false': {}}
        })

    # SPIKES (stalactite and stalagmite)
    blockstate(('spike', rock_type), 'tfc:spike/top', textures={
        ('texture', 'particle'): 'tfc:blocks/stonetypes/raw/%s' % rock_type,
    }, variants={
        'normal': None,
        'ceiling': {'true': {'x': 180}, 'false': {}},
        'base': {'true': {'model': 'tfc:spike/base'}, 'false': {}}
    })

    # (ROCK) STAIRS & SLABS
    for block_type in ['smooth', 'cobble', 'bricks']:
        blockstate(('stairs', block_type, rock_type), None, textures={
            ('top', 'bottom', 'side'): 'tfc:blocks/stonetypes/%s/%s' % (block_type, rock_type),
        }, variants=STAIR_VARIANTS, uvlock=True)
        blockstate(('slab', block_type, rock_type), 'half_slab', textures={
            ('top', 'bottom', 'side'): 'tfc:blocks/stonetypes/%s/%s' % (block_type, rock_type),
        }, variants={
            'half': {
                'bottom': {},
                'top': {'model': 'upper_slab'}
            }
        })
        cube_all(('double_slab', block_type, rock_type), 'tfc:blocks/stonetypes/%s/%s' % (block_type, rock_type))

    # (STONE) BUTTON
    blockstate(('stone', 'button', rock_type), 'button', textures={
        ('texture', 'particle'): 'tfc:blocks/stonetypes/raw/%s' % rock_type,
    }, variants={
        'powered': {
            'false': {},
            'true': {'model': 'button_pressed'}
        },
        'facing': {
            'up': {},
            'down': {'x': 180},
            'east': {'x': 90, 'y': 90},
            'west': {'x': 90, 'y': 270},
            'south': {'x': 90, 'y': 180},
            'north': {'x': 90},
        },
        'inventory': [{
            'model': 'button_inventory'
        }]
    })

# STONE ANVILS - only igneous types
for rock_type in ['granite', 'rhyolite', 'basalt', 'gabbro', 'diorite', 'andesite', 'dacite']:
    if rock_type not in ROCK_TYPES:
        raise Exception('The anvil rock types list has been modified, please fix this!')
    blockstate(('anvil', rock_type), 'tfc:stone_anvil', textures={
        ('all', 'particle'): 'tfc:blocks/stonetypes/raw/%s' % rock_type,
    })

for berry_type, size in BERRY_TYPES.items():
    # BERRY BUSH
    blockstate(('berry_bush', berry_type), 'tfc:berry_bush_%s' % size, textures={
        'texture': 'tfc:blocks/berry_bush/bush/%s' % berry_type,
        'particle': 'tfc:blocks/berry_bush/bush/%s' % berry_type,
    }, variants={
        'inventory': {'model': 'tfc:berry_bush_large'},
        'fruiting': {'true': {'textures': {'texture': 'tfc:blocks/berry_bush/berry/%s' % berry_type}}, 'false': {}},
    })
"""
# WOOD STUFF
for wood_type in WOOD_TYPES:
    # CHOPPING BLOCKS
    blockstate(('wood/devices/', 'chopping_block', wood_type), 'woodworking:devices/chopping_block', textures={
            ('particle', 'side'): 'tfc:blocks/wood/log/%s' % wood_type,
            'end': 'tfc:blocks/wood/top/%s' % wood_type,
            'up': 'woodworking:blocks/wood/devices/chopping_block'}, variants={
                'log_placed': {
                    'true': {'model' : 'woodworking:devices/chopping_block'},
                    'false': {'submodel' : 'woodworking:devices/chopping_block_log' }
                } 
            })

    # TABLES
    blockstate(('wood/furniture/', 'simple_table', wood_type), 'woodworking:furniture/simple_table', textures={
            ('particle', 'all'): 'tfc:blocks/wood/planks/%s' % wood_type
    }, variants={
        'normal': {'model' : 'woodworking:furniture/simple_table'}
    })

    # STOOLS
    blockstate(('wood/furniture/', 'simple_stool', wood_type), 'woodworking:furniture/simple_stool', textures={
            ('particle', 'all'): 'tfc:blocks/wood/planks/%s' % wood_type
    }, variants={
        'normal': {'model' : 'woodworking:furniture/simple_stool'}
    })
"""
    # LOG BLOCKS
    blockstate(('wood', 'log', wood_type), 'item/generated', textures={
        ('particle', 'side'): 'tfc:blocks/wood/log/%s' % wood_type,
        'end': 'tfc:blocks/wood/top/%s' % wood_type,
        'layer0': 'tfc:items/wood/log/%s' % wood_type,
    }, variants={
        'axis': {
            'y': {'model': 'cube_column'},
            'z': {'model': 'cube_column', 'x': 90},
            'x': {'model': 'cube_column', 'x': 90, 'y': 90},
            'none': {
                'model': 'cube_column',
                'textures': {'end': 'tfc:blocks/wood/log/%s' % wood_type}
            }
        },
        'small': {
            'true': {'model': 'tfc:small_log'},
            'false': {},
        }
    })

    # PLANKS BLOCKS
    cube_all(('wood', 'planks', wood_type), 'tfc:blocks/wood/planks/%s' % wood_type)
    # LEAVES BLOCKS
    if wood_type != 'palm':
        cube_all(('wood', 'leaves', wood_type), 'tfc:blocks/wood/leaves/%s' % wood_type, model='leaves')

    # FENCES
    blockstate(('wood', 'fence', wood_type), 'fence_post', textures={
        'texture': 'tfc:blocks/wood/planks/%s' % wood_type
    }, variants={
        'inventory': {'model': 'fence_inventory'},
        'north': {'true': {'submodel': 'fence_side'}, 'false': {}},
        'east': {'true': {'submodel': 'fence_side', 'y': 90}, 'false': {}},
        'south': {'true': {'submodel': 'fence_side', 'y': 180}, 'false': {}},
        'west': {'true': {'submodel': 'fence_side', 'y': 270}, 'false': {}},
    })

    # FENCE GATES
    blockstate(('wood', 'fence_gate', wood_type), 'fence_gate_closed', textures={
        'texture': 'tfc:blocks/wood/planks/%s' % wood_type
    }, variants={
        'inventory': [{}],
        'facing': {
            'south': {},
            'west': {'y': 90},
            'north': {'y': 180},
            'east': {'y': 270},
        },
        'open': {'true': {'model': 'fence_gate_open'}, 'false': {}},
        'in_wall': {'true': {'transform': {'translation': [0, -3 / 16, 0]}}, 'false': {}},
    })

    # SAPLINGS
    blockstate(('wood', 'sapling', wood_type), 'cross', textures={
        ('cross', 'layer0'): 'tfc:blocks/saplings/%s' % wood_type
    }, variants={
        'inventory': {
            'model': 'builtin/generated',
            'transform': 'forge:default-item'
        }
    })

    # DOORS
    blockstate(('wood', 'door', wood_type), None, textures={
        'bottom': 'tfc:blocks/wood/door/lower/%s' % wood_type,
        'top': 'tfc:blocks/wood/door/upper/%s' % wood_type,
    }, variants=DOOR_VARIANTS)

    # TOOL RACKS
    blockstate(('wood', 'tool_rack', wood_type), 'tfc:tool_rack', textures={
        'texture': 'tfc:blocks/wood/planks/%s' % wood_type,
        'particle': 'tfc:blocks/wood/planks/%s' % wood_type,
    }, variants={
        'facing': {
            'south': {},
            'west': {'y': 90},
            'north': {'y': 180},
            'east': {'y': 270},
        }
    })

    # (WOOD) STAIRS & SLABS
    blockstate(('stairs', 'wood', wood_type), None, textures={
        ('top', 'bottom', 'side'): 'tfc:blocks/wood/planks/%s' % wood_type,
    }, variants=STAIR_VARIANTS, uvlock=True)
    blockstate(('slab', 'wood', wood_type), 'half_slab', textures={
        ('top', 'bottom', 'side'): 'tfc:blocks/wood/planks/%s' % wood_type,
    }, variants={
        'half': {
            'bottom': {},
            'top': {'model': 'upper_slab'}
        }
    })
    cube_all(('double_slab', 'wood', wood_type), 'tfc:blocks/wood/planks/%s' % wood_type)

    # (WOOD) TRAPDOORS
    blockstate(('wood', 'trapdoor', wood_type), None, textures={
        'texture': 'tfc:blocks/wood/trapdoor/%s' % wood_type
    }, variants=TRAPDOOR_VARIANTS)

    # CHESTS
    blockstate(('wood', 'chest', wood_type), 'tfc:chest', textures={
        'texture': 'tfc:entity/chests/chest/%s' % wood_type,
        'particle': 'tfc:entity/chests/chest/%s' % wood_type,
    })
    blockstate(('wood', 'chest_trap', wood_type), 'tfc:chest', textures={
        'texture': 'tfc:entity/chests/chest_trap/%s' % wood_type,
        'particle': 'tfc:entity/chests/chest_trap/%s' % wood_type,
    })

    # (WOOD) BUTTON
    blockstate(('wood', 'button', wood_type), 'button', textures={
        ('texture', 'particle'): 'tfc:blocks/wood/planks/%s' % wood_type,
    }, variants={
        'powered': {
            'false': {},
            'true': {'model': 'button_pressed'}
        },
        'facing': {
            'up': {},
            'down': {'x': 180},
            'east': {'x': 90, 'y': 90},
            'west': {'x': 90, 'y': 270},
            'south': {'x': 90, 'y': 180},
            'north': {'x': 90},
        },
        'inventory': [{
            'model': 'button_inventory'
        }]
    })

    # BOOKSHELF
    blockstate(('wood', 'bookshelf', wood_type), 'tfc:bookshelf', textures={
        ('all', 'particle'): 'tfc:blocks/wood/planks/%s' % wood_type,
        ('north', 'south', 'east', 'west'): 'tfc:blocks/wood/bookshelf',
    })

    # WORKBENCH
    blockstate(('wood', 'workbench', wood_type), 'tfc:workbench', textures={
        ('all', 'particle'): 'tfc:blocks/wood/planks/%s' % wood_type,
        'top': 'tfc:blocks/wood/workbench_top',
        ('north', 'south'): 'tfc:blocks/wood/workbench_front',
        ('east', 'west'): 'tfc:blocks/wood/workbench_side',
    })

    # BARREL
    blockstate(('wood', 'barrel', wood_type), 'tfc:barrel', textures={
        ('particle', 'planks'): 'tfc:blocks/wood/planks/%s' % wood_type,
        'sheet': 'tfc:blocks/wood/sheets/%s' % wood_type,
        'hoop': 'tfc:blocks/barrelhoop',
    }, variants={
        'sealed': {
            'true': {'model': 'tfc:barrel_sealed'},
            'false': {},
        },
        'inventory': [{}]
    })

    # LOOM
    blockstate(('wood', 'loom', wood_type), 'tfc:loom', textures={
        'texture': 'tfc:blocks/wood/planks/%s' % wood_type,
        'particle': 'tfc:blocks/wood/planks/%s' % wood_type,
    }, variants={
        'facing': {
            'south': {},
            'west': {'y': 90},
            'north': {'y': 180},
            'east': {'y': 270},
        }
    })

    # SUPPORT
    blockstate(('wood', 'support', wood_type), 'tfc:support/vertical', textures={
        'texture': 'tfc:blocks/wood/sheets/%s' % wood_type,
        'particle': 'tfc:blocks/wood/sheets/%s' % wood_type,
    }, variants={
        'inventory': {'model': 'tfc:support/inventory'},
        'axis': {
            'y': {'model': 'tfc:support/vertical'}, 'x': {'model': 'tfc:support/horizontal'},
            'z': {'model': 'tfc:support/horizontal', 'y': 90}
        },
        'north': {'true': {'submodel': 'tfc:support/connection', 'y': 270}, 'false': {}},
        'east': {'true': {'submodel': 'tfc:support/connection'}, 'false': {}},
        'south': {'true': {'submodel': 'tfc:support/connection', 'y': 90}, 'false': {}},
        'west': {'true': {'submodel': 'tfc:support/connection', 'y': 180}, 'false': {}},
    })

# LEATHER / HIDES
blockstate(('placed_hide',), 'tfc:hide_rack', {})

# AGRICULTURE

for cropName, data in CROPS.items():
    texture = data['texture']
    blockstate(
        ('crop', cropName), data['model'], {texture: "tfc:blocks/crop/%s_0" % cropName},
        {
            'stage': dict(
                (
                    str(stage),
                    {'textures': {texture: "tfc:blocks/crop/%s_%d" % (cropName, stage)}}
                ) for stage in range(data['stages'])
            )
        }
    )
    blockstate(('dead_crop', cropName), data['model'], {texture: "tfc:blocks/crop/%s_dead" % cropName})
"""

#   _____ _
#  |_   _| |
#    | | | |_ ___ _ __ ___  ___
#    | | | __/ _ \ '_ ` _ \/ __|
#   _| |_| ||  __/ | | | | \__ \
#  |_____|\__\___|_| |_| |_|___/
# 
# ITEMS

# WOOD STUFF
for wood_type in WOOD_TYPES:
    item(('wood', 'lumber', wood_type), 'tfc:items/wood/lumber/%s' % wood_type)
    item(('wood', 'boat', wood_type), 'tfc:items/wood/boat/%s' % wood_type)
    item(('wood', 'split_log', wood_type), 'woodworking:items/wood/split_log/%s' % wood_type)

"""
# ORES
for ore_type in ORE_TYPES:
    if ORE_TYPES[ore_type]:
        for grade in ['poor', 'rich', 'small']:
            item(('ore', grade, ore_type), 'tfc:items/ore/%s/%s' % (grade, ore_type))
    item(('ore', 'normal', ore_type), 'tfc:items/ore/%s' % ore_type)

# ROCK_CATEGORIES
for rock_type in ROCK_TYPES:
    for item_type in ['rock', 'brick']:
        item((item_type, rock_type), 'tfc:items/stonetypes/%s/%s' % (item_type, rock_type))

# DOORS / TRAPDOORS
for wood_type in WOOD_TYPES:
    item(('wood', 'log', wood_type), 'tfc:items/wood/log/%s' % wood_type)
    item(('wood', 'door', wood_type), 'tfc:items/wood/door/%s' % wood_type)

    # Trapdoors are special - their item model needs to reference the blockstate #texture
    model(('item', 'wood', 'trapdoor', wood_type), 'block/trapdoor_bottom',
          {'texture': 'tfc:blocks/wood/trapdoor/%s' % wood_type})

# GEMS
for gem in GEM_TYPES:
    for grade in GEM_GRADES:
        item(('gem', grade, gem), 'tfc:items/gem/%s/%s' % (grade, gem))

# METALS
for item_type, tool_item in METAL_ITEMS.items():
    for metal, tool_metal in METAL_TYPES.items():
        if tool_item and not tool_metal:
            continue
        if item_type == 'anvil':
            model(('item', 'metal', 'anvil', metal), 'tfc:item/metal/anvil/transformations',
                  {'all': 'tfc:blocks/metal/%s' % metal})
        else:
            parent = 'item/handheld' if item_type in TOOLS else 'item/generated'
            if item_type in ['knife', 'javelin']:
                parent = 'tfc:item/handheld_flipped'
            item(('metal', item_type, metal), 'tfc:items/metal/%s/%s' % (item_type.replace('unfinished_', ''), metal),
                 parent=parent)
for metal in STEEL:
    for type in ['high_carbon', 'weak']:
        item(('metal', 'ingot', type + '_' + metal), 'tfc:items/metal/ingot/%s' % metal)
item(('metal', 'ingot', 'unknown'), 'tfc:items/metal/ingot/%s' % 'unknown')

# ROCK TOOLS
for rock_cat in ROCK_CATEGORIES:
    for item_type in ['axe', 'shovel', 'hoe', 'knife', 'javelin', 'hammer']:
        # tools
        parent = 'item/handheld'
        if item_type in ['knife', 'javelin']:
            parent = 'tfc:item/handheld_flipped'
        item(('stone', item_type, rock_cat), 'tfc:items/stone/%s' % item_type, parent=parent)
        # tool heads
        item(('stone', item_type + '_head', rock_cat), 'tfc:items/stone/%s_head' % item_type)

# POWDERS
for powder in POWDERS:
    item(('powder', powder), 'tfc:items/powder/%s' % powder)

# GOLDPAN
for type in ['empty', 'sand', 'gravel', 'clay', 'dirt']:
    item(('goldpan', type), 'tfc:items/goldpan/%s' % type)

# CERAMICS
_heads = [x + '_head' for x in TOOLS] + [x + '_blade' for x in TOOLS]
for item_type in METAL_ITEMS:
    if item_type not in _heads:
        continue
    # unfired molds
    item(('ceramics', 'unfired', 'mold', item_type), 'tfc:items/ceramics/unfired/mold/%s' % item_type)
    # fired, empty molds
    item(('ceramics', 'fired', 'mold', item_type, 'empty'), 'tfc:items/ceramics/fired/mold/%s/empty' % item_type)
    # fired, filled molds
    for metal in ('copper', 'bronze', 'black_bronze', 'bismuth_bronze'):
        item(('ceramics', 'fired', 'mold', item_type, metal),
             'tfc:items/ceramics/fired/mold/%s/%s' % (item_type, metal))
del _heads

# unfired ingot molds
item(('ceramics', 'unfired', 'mold', 'ingot'), 'tfc:items/ceramics/unfired/mold/ingot')
# fired ingot molds for all metals
_ingotMetals = {}  # metal name -> texture
for metal in METAL_TYPES.keys():
    _ingotMetals[metal] = metal
for name in ('empty', 'unknown'):
    _ingotMetals[name] = name
for prefix in ('weak', 'high_carbon'):
    for steel in ('blue_steel', 'red_steel', 'black_steel', 'steel'):
        if prefix != 'weak' or steel != 'black_steel':
            _ingotMetals['_'.join((prefix, steel))] = steel

for metal, texture in _ingotMetals.items():
    item(('ceramics', 'fired', 'mold', 'ingot', metal), 'tfc:items/ceramics/fired/mold/ingot/' + texture)
del _ingotMetals

item(('ceramics', 'unfired', 'vessel'), 'tfc:items/ceramics/unfired/vessel')
item(('ceramics', 'fired', 'vessel'), 'tfc:items/ceramics/fired/vessel')
item(('ceramics', 'unfired', 'vessel_glazed'), 'tfc:items/ceramics/unfired/vessel',
     'tfc:items/ceramics/fired/vessel_overlay')
item(('ceramics', 'fired', 'vessel_glazed'), 'tfc:items/ceramics/fired/vessel',
     'tfc:items/ceramics/fired/vessel_overlay')

item(('ceramics', 'unfired', 'spindle'), 'tfc:items/ceramics/unfired/spindle')
item(('ceramics', 'fired', 'spindle'), 'tfc:items/ceramics/fired/spindle')
item(('ceramics', 'unfired', 'pot'), 'tfc:items/ceramics/unfired/pot')
item(('ceramics', 'fired', 'pot'), 'tfc:items/ceramics/fired/pot')
item(('ceramics', 'unfired', 'bowl'), 'tfc:items/ceramics/unfired/bowl')
item(('ceramics', 'fired', 'bowl'), 'tfc:items/ceramics/fired/bowl')
item(('ceramics', 'unfired', 'fire_brick'), 'tfc:items/ceramics/unfired/fire_brick')
item(('ceramics', 'fired', 'fire_brick'), 'tfc:items/ceramics/fired/fire_brick')
item(('ceramics', 'unfired', 'jug'), 'tfc:items/ceramics/unfired/jug')

item(('ceramics', 'fire_clay'), 'tfc:items/ceramics/fire_clay')

# FLAT
for rock_cat in ROCK_TYPES:
    item(('flat', rock_cat), 'tfc:items/flat/%s' % rock_cat)

# LEATHER / HIDES

for size in ('small', 'medium', 'large'):
    for hide in ('raw', 'scraped', 'soaked', 'prepared', 'sheepskin'):
        item(('hide', hide, size), 'tfc:items/hide/%s/%s' % (size, hide))

# AGRICULTURE
for food in FOODS:
    item(('food', food), 'tfc:items/food/%s' % food)

for crop in CROPS.keys():
    item(('crop', 'seeds', crop), 'tfc:items/crop/seeds/%s' % crop)
"""